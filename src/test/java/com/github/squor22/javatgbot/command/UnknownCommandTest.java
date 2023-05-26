package com.github.squor22.javatgbot.command;

import com.github.squor22.javatgbot.bot.command.Command;
import com.github.squor22.javatgbot.bot.command.UnknownCommand;
import org.junit.jupiter.api.DisplayName;

import static com.github.squor22.javatgbot.bot.command.UnknownCommand.UNKNOWN_MESSAGE;

@DisplayName("Unit-level testing for UnknownCommand")
public class UnknownCommandTest  extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return "/fdgdfgdfgdbd";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}