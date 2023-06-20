package com.github.squor22.javatgbot.command;

import com.github.squor22.javatgbot.command.commands.NoCommand;
import org.junit.jupiter.api.DisplayName;

import static com.github.squor22.javatgbot.command.CommandName.NO;
import static com.github.squor22.javatgbot.command.commands.NoCommand.NO_MESSAGE;

@DisplayName("Unit-level testing for NoCommand")
public class NoCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return NO.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}
