package com.github.squor22.javatgbot.bot.command;

import com.github.squor22.javatgbot.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public final static String START_MESSAGE = "Hi, I'm bot made using Java";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage((update.getMessage()).getChatId().toString(), START_MESSAGE);
    }
}
