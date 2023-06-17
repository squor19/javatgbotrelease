package com.github.squor22.javatgbot.bot.service;

import java.util.List;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
    void sendMessage(String chatId, List<String> posts);
}
