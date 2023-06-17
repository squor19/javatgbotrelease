package com.github.squor22.javatgbot.bot.service;

import java.util.List;

public interface SendBotMessageService {

    void sendMessage(String chatId, String message);
    void sendMessages(String chatId, List<String> posts);
}
