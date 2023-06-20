package com.github.squor22.javatgbot.command.commands;

import com.github.squor22.javatgbot.command.Command;
import com.github.squor22.javatgbot.command.CommandName;
import com.github.squor22.javatgbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AdminHelpCommand implements Command {

    public static final String ADMIN_HELP_MESSAGE = String.format("✨<b>Доступні команди адміна</b>✨\n\n"
                    + "<b>Отримати статистику</b>\n"
                    + "%s - статистика бота\n",
            CommandName.STAT.getCommandName());

    private final SendBotMessageService sendBotMessageService;

    public AdminHelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), ADMIN_HELP_MESSAGE);
    }
}