package com.github.squor22.javatgbot.service;

import com.github.squor22.javatgbot.dto.GroupDiscussionInfo;
import com.github.squor22.javatgbot.entity.GroupSub;

import java.util.List;
import java.util.Optional;


public interface GroupSubService {
    GroupSub save(Long chatId, GroupDiscussionInfo groupDiscussionInfo);

    GroupSub save(GroupSub groupSub);

    List<GroupSub> findAll();

    Optional<GroupSub> findById(Integer id);
}
