package com.github.squor22.javatgbot.db.service;

import com.github.squor22.javatgbot.client.dto.StatisticDTO;

/**
 * Service for getting bot statistics.
 */
public interface StatisticsService {
    StatisticDTO countBotStatistic();
}