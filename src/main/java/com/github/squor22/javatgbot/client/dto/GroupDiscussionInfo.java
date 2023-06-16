package com.github.squor22.javatgbot.client.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class GroupDiscussionInfo extends GroupInfo {

    private UserDiscussionInfo userDiscussionInfo;
    private Integer commentsCount;
}
