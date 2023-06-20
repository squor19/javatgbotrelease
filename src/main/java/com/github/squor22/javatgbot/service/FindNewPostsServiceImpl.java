package com.github.squor22.javatgbot.service;

import com.github.squor22.javatgbot.client.PostClient;
import com.github.squor22.javatgbot.dto.PostInfo;
import com.github.squor22.javatgbot.entity.GroupSub;
import com.github.squor22.javatgbot.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindNewPostsServiceImpl implements FindNewPostsService {

    public static final String JAVARUSH_WEB_POST_FORMAT = "https://javarush.com/groups/posts/%s";

    private final GroupSubService groupSubService;
    private final PostClient postClient;
    private final SendBotMessageService sendMessageService;

    @Autowired
    public FindNewPostsServiceImpl(GroupSubService groupSubService,
                                   SendBotMessageService sendMessageService,
                                   PostClient postClient) {
        this.groupSubService = groupSubService;
        this.sendMessageService = sendMessageService;
        this.postClient = postClient;
    }

    @Override
    public void findNewArticles() {
        groupSubService.findAll().forEach(gSub -> {
            List<PostInfo> newPosts = postClient.findNewPosts(gSub.getId(), gSub.getLastPostId());

            setNewLastPostId(gSub, newPosts);

            notifySubscribersAboutNewPost(gSub, newPosts);
        });
    }

    private void notifySubscribersAboutNewPost(GroupSub gSub, List<PostInfo> newPosts) {
        Collections.reverse(newPosts);
        List<String> messagesWithNewPosts = newPosts.stream()
                .map(post -> String.format("✨Вийшла нова стаття <b>%s</b> в групі <b>%s</b>.✨\n\n" +
                                "<b>Опис:</b> %s\n\n" +
                                "<b>Лінк:</b> %s\n",
                        post.getTitle(), gSub.getTitle(), post.getDescription(), getPostUrl(post.getKey())))
                .collect(Collectors.toList());

        gSub.getUsers().stream()
                .filter(TelegramUser::isActive)
                .forEach(it -> sendMessageService.sendMessage(it.getChatId(), messagesWithNewPosts));
    }

    private void setNewLastPostId(GroupSub gSub, List<PostInfo> newPosts) {
        newPosts.stream().mapToInt(PostInfo::getId).max()
                .ifPresent(id -> {
                    gSub.setLastPostId(id);
                    groupSubService.save(gSub);
                });
    }

    private String getPostUrl(String key) {
        return String.format(JAVARUSH_WEB_POST_FORMAT, key);
    }
}
