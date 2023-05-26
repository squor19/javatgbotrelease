package com.github.squor22.javatgbot.bot.command;


import com.github.squor22.javatgbot.bot.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

import static com.github.squor22.javatgbot.bot.command.CommandName.*;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;

    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(TEST.getCommandName(), new TestCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
