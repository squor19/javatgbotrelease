package com.github.squor22.javatgbot.bot.command.commands;

import com.github.squor22.javatgbot.bot.command.Command;
import com.github.squor22.javatgbot.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TestCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String TEST_MESSAGE = "Hello, this is just let you know that this is a test message :)";

    public TestCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), TEST_MESSAGE);
    }
}
