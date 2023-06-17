package com.github.squor22.javatgbot.db.service;

public interface FindNewArticleService {

    /**
     * Find new articles and notify subscribers about it.
     */
    void findNewArticles();
}