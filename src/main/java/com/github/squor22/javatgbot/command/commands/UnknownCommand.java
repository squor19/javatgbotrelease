package com.github.squor22.javatgbot.command.commands;

import com.github.squor22.javatgbot.command.Command;
import com.github.squor22.javatgbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE = "I don't understand you \uD83D\uDE1F, type /help to see what I can understand.";

    public final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update){
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), UNKNOWN_MESSAGE);
    }
}
