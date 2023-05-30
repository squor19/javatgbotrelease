package com.github.squor22.javatgbot.bot;

import com.github.squor22.javatgbot.bot.command.CommandContainer;
import com.github.squor22.javatgbot.bot.config.BotConfig;
import com.github.squor22.javatgbot.bot.service.SendBotMessageServiceImpl;
import com.github.squor22.javatgbot.repository.service.TelegramUserService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.squor22.javatgbot.bot.command.CommandName.NO;

@Component
public class JavaTgBot extends TelegramLongPollingBot{

    final BotConfig config;

    public static String COMMAND_PREFIX = "/";

    private final CommandContainer commandContainer;

    public JavaTgBot(BotConfig config, TelegramUserService telegramUserService) {
        super(config.getBotToken());
        this.config = config;
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), telegramUserService);
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();

                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }
    }
}
