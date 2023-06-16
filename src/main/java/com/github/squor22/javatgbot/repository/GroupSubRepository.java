package com.github.squor22.javatgbot.repository;

import com.github.squor22.javatgbot.repository.entity.GroupSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@link Repository} for {@link GroupSub} entity.
 */
@Repository
public interface GroupSubRepository extends JpaRepository<GroupSub, Integer> {
}