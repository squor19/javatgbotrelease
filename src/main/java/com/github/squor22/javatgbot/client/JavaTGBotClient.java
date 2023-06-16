package com.github.squor22.javatgbot.client;

import com.github.squor22.javatgbot.client.dto.*;
import com.github.squor22.javatgbot.repository.entity.GroupSub;

import java.util.List;

public interface JavaTGBotClient {
    /**
     * Get all the {@link GroupInfo} filtered by provided {@link GroupRequestArgs}.
     *
     * @param requestArgs provided {@link GroupRequestArgs}.
     * @return the collection of the {@link GroupInfo} objects.
     */
    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);

    /**
     * Get all the {@link GroupDiscussionInfo} filtered by provided {@link GroupRequestArgs}.
     *
     * @param requestArgs provided {@link GroupRequestArgs}
     * @return the collection of the {@link GroupDiscussionInfo} objects.
     */
    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);

    /**
     * Get count of groups filtered by provided {@link GroupRequestArgs}.
     *
     * @param countRequestArgs provided {@link GroupsCountRequestArgs}.
     * @return the count of the groups.
     */
    Integer getGroupCount(GroupsCountRequestArgs countRequestArgs);

    /**
     * Get {@link GroupDiscussionInfo} by provided ID.
     *
     * @param id provided ID.
     * @return {@link GroupDiscussionInfo} object.
     */
    GroupDiscussionInfo getGroupById(Integer id);

    List<PostInfo> findNewPosts(Integer id, Integer lastArticleId);
}
