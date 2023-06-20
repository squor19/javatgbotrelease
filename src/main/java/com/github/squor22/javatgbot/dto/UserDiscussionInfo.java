package com.github.squor22.javatgbot.dto;

import lombok.Data;

@Data
public class UserDiscussionInfo {
    private Boolean isBookmarked;
    private Integer lastTime;
    private Integer newCommentsCount;
}
