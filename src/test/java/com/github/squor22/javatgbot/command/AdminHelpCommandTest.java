package com.github.squor22.javatgbot.command;

import com.github.squor22.javatgbot.command.commands.AdminHelpCommand;
import org.junit.jupiter.api.DisplayName;

import static com.github.squor22.javatgbot.command.CommandName.ADMIN_HELP;
import static com.github.squor22.javatgbot.command.commands.AdminHelpCommand.ADMIN_HELP_MESSAGE;

@DisplayName("Unit-level testing for AdminHelpCommand")
public class AdminHelpCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return ADMIN_HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return ADMIN_HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new AdminHelpCommand(sendBotMessageService);
    }
}