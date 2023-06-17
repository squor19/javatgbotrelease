package com.github.squor22.javatgbot.bot.command.commands;

import com.github.squor22.javatgbot.bot.command.Command;
import com.github.squor22.javatgbot.bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.squor22.javatgbot.bot.command.CommandName.*;

public class HelpCommand implements Command {
    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("✨Дотупные команды✨\n\n"

                    + "Почати\\закінчити роботу з ботом:\n"
                    + "%s - почати роботу з ботом\n"
                    + "%s - призупинити роботу з ботом\n\n"

                    + "Робота з підписками на групи:\n"
                    + "%s - підписатися на групу постів\n"
                    + "%s - відписатися від групи постів\n"
                    + "%s - отримати список груп, на які підписаний\n\n"

                    + "%s - отримати допомогу в роботі з ботом\n",
            START.getCommandName(), STOP.getCommandName(), ADD_GROUP_SUB.getCommandName(), DELETE_GROUP_SUB.getCommandName(),
            LIST_GROUP_SUB.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), HELP_MESSAGE);
    }
}
