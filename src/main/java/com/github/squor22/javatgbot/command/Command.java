package com.github.squor22.javatgbot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {

    void execute(Update update);
}
