package com.github.squor22.javatgbot.client;

import com.github.squor22.javatgbot.dto.PostInfo;

import java.util.List;

public interface PostClient {

    /**
     * Find new posts since lastPostId in provided group.
     *
     * @param groupId provided group ID.
     * @param lastPostId provided last post ID.
     * @return the collection of the new {@link PostInfo}.
     */
    public List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId);
}