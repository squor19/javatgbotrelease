package com.github.squor22.javatgbot.bot.command.commands;

import com.github.squor22.javatgbot.bot.command.Command;

import static com.github.squor22.javatgbot.bot.command.CommandName.STAT;
import static java.lang.String.format;

import com.github.squor22.javatgbot.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AdminHelpCommand implements Command {

    public static final String ADMIN_HELP_MESSAGE = format("✨<b>Доступные команды админа</b>✨\n\n"
                    + "<b>Получить статистику</b>\n"
                    + "%s - статистика бота\n",
            STAT.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    public AdminHelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), ADMIN_HELP_MESSAGE);
    }
}