package com.github.squor22.javatgbot.command;


import com.github.squor22.javatgbot.command.annotation.AdminCommand;
import com.github.squor22.javatgbot.command.commands.*;
import com.github.squor22.javatgbot.service.SendBotMessageService;
import com.github.squor22.javatgbot.client.GroupClient;
import com.github.squor22.javatgbot.service.GroupSubService;
import com.github.squor22.javatgbot.service.StatisticsService;
import com.github.squor22.javatgbot.service.TelegramUserService;
import com.google.common.collect.ImmutableMap;

import java.util.List;

import static java.util.Objects.nonNull;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;
    private final List<String> admins;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, GroupClient javaTGBotClient, GroupSubService groupSubService, List<String> admins, StatisticsService statisticsService) {
        this.admins = admins;

        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandName.START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(CommandName.STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(CommandName.HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(CommandName.NO.getCommandName(), new NoCommand(sendBotMessageService))
                .put(CommandName.TEST.getCommandName(), new TestCommand(sendBotMessageService))
                .put(CommandName.STAT.getCommandName(), new StatCommand(sendBotMessageService, statisticsService))
                .put(CommandName.ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, javaTGBotClient, groupSubService))
                .put(CommandName.LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService))
                .put(CommandName.DELETE_GROUP_SUB.getCommandName(), new DeleteGroupSubCommand(sendBotMessageService, groupSubService, telegramUserService))
                .put(CommandName.ADMIN_HELP.getCommandName(), new AdminHelpCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retrieveCommand(String commandIdentifier, String username) {
        Command orDefault = commandMap.getOrDefault(commandIdentifier, unknownCommand);
        if (isAdminCommand(orDefault)) {
            if (admins.contains(username)) {
                return orDefault;
            } else {
                return unknownCommand;
            }
        }
        return orDefault;
    }

    private boolean isAdminCommand(Command command) {
        return nonNull(command.getClass().getAnnotation(AdminCommand.class));
    }
}
