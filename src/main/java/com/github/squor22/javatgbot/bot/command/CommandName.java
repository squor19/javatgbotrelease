package com.github.squor22.javatgbot.bot.command;

public enum CommandName {

    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    TEST("/test"),
    NO("/no"),
    STAT("/stat");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}
