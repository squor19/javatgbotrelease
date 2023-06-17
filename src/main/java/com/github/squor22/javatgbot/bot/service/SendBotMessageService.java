package com.github.squor22.javatgbot.bot.service;

import java.util.List;

public interface SendBotMessageService {

    void sendMessage(Long chatId, String message);
    void sendMessage(Long chatId, List<String> posts);
}
