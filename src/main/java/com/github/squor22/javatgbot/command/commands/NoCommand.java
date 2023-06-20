package com.github.squor22.javatgbot.command.commands;

import com.github.squor22.javatgbot.command.Command;
import com.github.squor22.javatgbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String NO_MESSAGE = "I support commands which start with slash(/).\n\n"
            + "To see commands list just type /help";

    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), NO_MESSAGE);
    }
}
