package com.github.squor22.javatgbot.db.service;

import com.github.squor22.javatgbot.bot.service.SendBotMessageService;
import com.github.squor22.javatgbot.client.JavaTGBotClient;
import com.github.squor22.javatgbot.client.dto.PostInfo;
import com.github.squor22.javatgbot.db.entity.GroupSub;
import com.github.squor22.javatgbot.db.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindNewArticleServiceImpl implements FindNewPostsService {

    public static final String JAVARUSH_WEB_POST_FORMAT = "https://javarush.com/groups/posts/%s";

    private final GroupSubService groupSubService;
    private final JavaTGBotClient javaTGBotClient;
    private final SendBotMessageService sendMessageService;

    @Autowired
    public FindNewArticleServiceImpl(GroupSubService groupSubService,
                                     JavaTGBotClient javaRushPostClient,
                                     SendBotMessageService sendMessageService) {
        this.groupSubService = groupSubService;
        this.javaTGBotClient = javaRushPostClient;
        this.sendMessageService = sendMessageService;
    }

    @Override
    public void findNewArticles() {
        groupSubService.findAll().forEach(gSub -> {
            List<PostInfo> newPosts = javaTGBotClient.findNewPosts(gSub.getId(), gSub.getLastPostId());

            setNewLastArticleId(gSub, newPosts);

            notifySubscribersAboutNewArticles(gSub, newPosts);
        });
    }

    private void notifySubscribersAboutNewArticles(GroupSub gSub, List<PostInfo> newPosts) {
        Collections.reverse(newPosts);
        List<String> messagesWithNewPosts = newPosts.stream()
                .map(post -> String.format("✨Вышла новая статья <b>%s</b> в группе <b>%s</b>.✨\n\n" +
                                "<b>Описание:</b> %s\n\n" +
                                "<b>Ссылка:</b> %s\n",
                        post.getTitle(), gSub.getTitle(), post.getDescription(), getPostUrl(post.getKey())))
                .collect(Collectors.toList());

        gSub.getUsers().stream()
                .filter(TelegramUser::isActive)
                .forEach(it -> sendMessageService.sendMessage(it.getChatId().toString(), messagesWithNewPosts));
    }

    private void setNewLastArticleId(GroupSub gSub, List<PostInfo> newPosts) {
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