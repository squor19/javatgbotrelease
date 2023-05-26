package com.github.squor22.javatgbot.command;

import com.github.squor22.javatgbot.bot.command.Command;
import com.github.squor22.javatgbot.bot.command.HelpCommand;
import org.junit.jupiter.api.DisplayName;

import static com.github.squor22.javatgbot.bot.command.CommandName.HELP;
import static com.github.squor22.javatgbot.bot.command.HelpCommand.HELP_MESSAGE;

@DisplayName("Unit-level testing for HelpCommand")
public class HelpCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}
