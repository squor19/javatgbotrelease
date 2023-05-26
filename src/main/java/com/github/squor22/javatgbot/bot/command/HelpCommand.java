package com.github.squor22.javatgbot.bot.command;

import com.github.squor22.javatgbot.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.squor22.javatgbot.bot.command.CommandName.*;

public class HelpCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = "✨<b>Available commands</b>✨\n\n"
        + "<b>Start stop bot</b>\n"
        + START.getCommandName() + " - start bot\n"
        + STOP.getCommandName() + " - stop bot\n\n"
        + HELP.getCommandName() + " - info and help\n";

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
