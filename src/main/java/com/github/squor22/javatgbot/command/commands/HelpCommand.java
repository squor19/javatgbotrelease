package com.github.squor22.javatgbot.command.commands;

import com.github.squor22.javatgbot.command.Command;
import com.github.squor22.javatgbot.command.CommandName;
import com.github.squor22.javatgbot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨Доступні команди✨\n\n"

                    + "Почати\\закінчити роботу з ботом:\n"
                    + "%s - почати роботу з ботом\n"
                    + "%s - призупинити роботу з ботом\n\n"

                    + "Робота з підписками на групи:\n"
                    + "%s - підписатися на групу постів\n"
                    + "%s - відписатися від групи постів\n"
                    + "%s - отримати список груп, на які підписаний\n\n"

                    + "%s - отримати допомогу в роботі з ботом\n",
            CommandName.START.getCommandName(), CommandName.STOP.getCommandName(), CommandName.ADD_GROUP_SUB.getCommandName(), CommandName.DELETE_GROUP_SUB.getCommandName(),
            CommandName.LIST_GROUP_SUB.getCommandName(), CommandName.HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), HELP_MESSAGE);
    }
}
