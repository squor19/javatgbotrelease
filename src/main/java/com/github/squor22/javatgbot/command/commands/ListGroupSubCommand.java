package com.github.squor22.javatgbot.command.commands;

import com.github.squor22.javatgbot.command.Command;
import com.github.squor22.javatgbot.service.SendBotMessageService;
import com.github.squor22.javatgbot.entity.GroupSub;
import com.github.squor22.javatgbot.entity.TelegramUser;
import com.github.squor22.javatgbot.service.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.ws.rs.NotFoundException;
import java.util.stream.Collectors;

import static com.github.squor22.javatgbot.command.CommandUtils.getChatId;

/**
 * {@link Command} for getting list of {@link GroupSub}.
 */
public class ListGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public ListGroupSubCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        //todo add exception handling
        TelegramUser telegramUser = telegramUserService.findByChatId(getChatId(update))
                .orElseThrow(NotFoundException::new);

        String message = "я знайшов всі підписки на групи: \n\n";
        String collectedGroups = telegramUser.getGroupSubs().stream()
                .map(it -> "Група: " + it.getTitle() + " , ID = " + it.getId() + " \n")
                .collect(Collectors.joining());

        sendBotMessageService.sendMessage(telegramUser.getChatId(), message + collectedGroups);
    }
}