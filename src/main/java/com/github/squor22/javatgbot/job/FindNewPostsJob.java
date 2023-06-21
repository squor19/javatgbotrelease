package com.github.squor22.javatgbot.job;

import com.github.squor22.javatgbot.config.JobConfig;
import com.github.squor22.javatgbot.service.FindNewPostsService;
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

    private final JobConfig jobConfig;

    private final FindNewPostsService findNewPostService;

    @Autowired
    public FindNewPostsJob(FindNewPostsService findNewArticleService, JobConfig jobConfig) {
        this.findNewPostService = findNewArticleService;
        this.jobConfig = jobConfig;
    }

    @Scheduled(fixedRateString = "#{jobConfig.getRate()}")
    public void findNewPosts() {
        LocalDateTime start = LocalDateTime.now();

        log.info("Find new article job started.");

        findNewPostService.findNewArticles();

        LocalDateTime end = LocalDateTime.now();

        log.info("Find new articles job finished. Took seconds: {}",
                end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC));
    }
}
