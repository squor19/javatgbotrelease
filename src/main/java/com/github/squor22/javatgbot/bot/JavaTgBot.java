package com.github.squor22.javatgbot.bot;

import com.github.squor22.javatgbot.bot.command.CommandContainer;
import com.github.squor22.javatgbot.bot.config.BotConfig;
import com.github.squor22.javatgbot.bot.service.SendBotMessageServiceImpl;
import com.github.squor22.javatgbot.client.JavaTGBotClient;
import com.github.squor22.javatgbot.db.service.GroupSubService;
import com.github.squor22.javatgbot.db.service.StatisticsService;
import com.github.squor22.javatgbot.db.service.TelegramUserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;

import static com.github.squor22.javatgbot.bot.command.CommandName.NO;

@Component
public class JavaTgBot extends TelegramLongPollingBot{

    final BotConfig config;

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;





    public JavaTgBot(BotConfig config, TelegramUserService telegramUserService, JavaTGBotClient javaTGBotClient, GroupSubService groupSubService, StatisticsService statisticsService) {
        super(config.getBotToken());
        this.config = config;
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService, javaTGBotClient, groupSubService, config.getAdmins(), statisticsService);
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            Message msg = update.getMessage();
            User user = msg.getFrom();
            String username = user.getUserName();
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier, username).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName(), username).execute(update);
            }
        }
    }
}
