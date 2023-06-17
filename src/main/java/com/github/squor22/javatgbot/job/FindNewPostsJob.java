package com.github.squor22.javatgbot.job;

import com.github.squor22.javatgbot.db.service.FindNewPostsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Job for finding new articles.
 */
@Slf4j
@Component
public class FindNewPostsJob {

    private final FindNewPostsService findNewArticleService;

    @Autowired
    public FindNewPostsJob(FindNewPostsService findNewArticleService) {
        this.findNewArticleService = findNewArticleService;
    }

    @Scheduled(fixedRateString = "${bot.recountNewPostsFixedRate}")
    public void findNewPosts() {
        LocalDateTime start = LocalDateTime.now();

        log.info("Find new article job started.");

        findNewArticleService.findNewArticles();

        LocalDateTime end = LocalDateTime.now();

        log.info("Find new articles job finished. Took seconds: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}