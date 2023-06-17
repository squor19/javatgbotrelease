package com.github.squor22.javatgbot.db.service;

import com.github.squor22.javatgbot.client.dto.GroupDiscussionInfo;
import com.github.squor22.javatgbot.db.entity.GroupSub;

import java.util.List;
import java.util.Optional;


public interface GroupSubService {
    GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo);

    GroupSub save(GroupSub groupSub);

    List<GroupSub> findAll();

    Optional<GroupSub> findById(Integer id);
}
