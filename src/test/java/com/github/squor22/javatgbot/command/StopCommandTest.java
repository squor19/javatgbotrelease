package com.github.squor22.javatgbot.command;

import com.github.squor22.javatgbot.command.commands.StopCommand;
import org.junit.jupiter.api.DisplayName;

import static com.github.squor22.javatgbot.command.CommandName.STOP;
import static com.github.squor22.javatgbot.command.commands.StopCommand.STOP_MESSAGE;

@DisplayName("Unit-level testing for StopCommand")
public class StopCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService, telegramUserService);
    }
}
