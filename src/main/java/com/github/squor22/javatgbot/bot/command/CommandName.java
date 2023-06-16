package com.github.squor22.javatgbot.bot.command;

public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    TEST("/test"),
    NO("/no"),
    STAT("/stat"),
    ADD_GROUP_SUB("/addgroupsub"),
    LIST_GROUP_SUB("/listgroupsub"),
    DELETE_GROUP_SUB("/deletegroupsub");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
