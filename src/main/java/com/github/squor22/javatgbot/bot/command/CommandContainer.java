package com.github.squor22.javatgbot.bot.command;


import com.github.squor22.javatgbot.bot.command.commands.*;
import com.github.squor22.javatgbot.bot.service.SendBotMessageService;
import com.github.squor22.javatgbot.client.JavaTGBotClient;
import com.github.squor22.javatgbot.repository.service.GroupSubService;
import com.github.squor22.javatgbot.repository.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;

import static com.github.squor22.javatgbot.bot.command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;

    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, JavaTGBotClient javaTGBotClient, GroupSubService groupSubService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(TEST.getCommandName(), new TestCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, javaTGBotClient, groupSubService))
                .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService))
                .put(DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubCommand(sendBotMessageService, groupSubService, telegramUserService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
