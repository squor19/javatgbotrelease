package com.github.squor22.javatgbot.bot;

import com.github.squor22.javatgbot.JavaTelegramBotApplication;
import com.github.squor22.javatgbot.command.CommandContainer;
import com.github.squor22.javatgbot.config.BotConfig;
import com.github.squor22.javatgbot.service.SendBotMessageServiceImpl;
import com.github.squor22.javatgbot.client.GroupClient;
import com.github.squor22.javatgbot.service.GroupSubService;
import com.github.squor22.javatgbot.service.StatisticsService;
import com.github.squor22.javatgbot.service.TelegramUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.squor22.javatgbot.command.CommandName.NO;

@Component
public class JavaTgBot extends TelegramLongPollingBot{

    final BotConfig config;

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    private static final Logger logger = LoggerFactory.getLogger(JavaTelegramBotApplication.class);



    public JavaTgBot(BotConfig config, TelegramUserService telegramUserService, GroupClient javaTGBotClient, GroupSubService groupSubService, StatisticsService statisticsService) {
        super(config.getBotToken());
        logger.info("I started my life");
        this.config = config;
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService, javaTGBotClient, groupSubService, config.getAdmins(), statisticsService);
        logger.info("I started my life");
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            logger.info("Have got a message: _______________________________ :" + update.getMessage());
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
