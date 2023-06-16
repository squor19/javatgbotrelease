package com.github.squor22.javatgbot.repository.service;

public interface FindNewArticleService {

    /**
     * Find new articles and notify subscribers about it.
     */
    void findNewArticles();
}