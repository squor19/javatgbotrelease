package com.github.squor22.javatgbot.service;

import com.github.squor22.javatgbot.dto.StatisticDTO;

/**
 * Service for getting bot statistics.
 */
public interface StatisticsService {
    StatisticDTO countBotStatistic();
}