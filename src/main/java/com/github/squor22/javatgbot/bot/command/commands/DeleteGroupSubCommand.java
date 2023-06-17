package com.github.squor22.javatgbot.bot.command.commands;

import com.github.squor22.javatgbot.bot.command.Command;
import com.github.squor22.javatgbot.db.service.GroupSubService;
import com.github.squor22.javatgbot.bot.service.SendBotMessageService;
import com.github.squor22.javatgbot.db.entity.GroupSub;
import com.github.squor22.javatgbot.db.entity.TelegramUser;
import com.github.squor22.javatgbot.db.service.TelegramUserService;
import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.squor22.javatgbot.bot.command.CommandName.DELETE_GROUP_SUB;
import static com.github.squor22.javatgbot.bot.command.CommandUtils.getChatId;
import static com.github.squor22.javatgbot.bot.command.CommandUtils.getMessage;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class DeleteGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    private final GroupSubService groupSubService;

    public DeleteGroupSubCommand(SendBotMessageService sendBotMessageService, GroupSubService groupSubService,
                                 TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.groupSubService = groupSubService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        if (getMessage(update).equalsIgnoreCase(DELETE_GROUP_SUB.getCommandName())) {
            sendGroupIdList(getChatId(update));
            return;
        }
        String groupId = getMessage(update).split(SPACE)[1];
        Long chatId = getChatId(update);
        if (isNumeric(groupId)) {
            Optional<GroupSub> optionalGroupSub = groupSubService.findById(Integer.valueOf(groupId));
            if (optionalGroupSub.isPresent()) {
                GroupSub groupSub = optionalGroupSub.get();
                TelegramUser telegramUser = telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
                groupSub.getUsers().remove(telegramUser);
                groupSubService.save(groupSub);
                sendBotMessageService.sendMessage(chatId, String.format("Видалив підписку на групу: %s", groupSub.getTitle()));
            } else {
                sendBotMessageService.sendMessage(chatId, "Не знайшов такої групи =/");
            }
        } else {
            sendBotMessageService.sendMessage(chatId, "неправильний формат групи.\n " +
                    "ID повинне бути цілим додатнім числом");
        }
    }

    private void sendGroupIdList(Long chatId) {
        String message;
        List<GroupSub> groupSubs = telegramUserService.findByChatId(chatId)
                .orElseThrow(NotFoundException::new)
                .getGroupSubs();
        if (CollectionUtils.isEmpty(groupSubs)) {
            message = "Поки немає підписок на групи. Щоб додати підписку напиши /addGroupSub";
        } else {
            message = "Щоб видалити підписку на групу - впиши команду разом з ID групи. \n" +
                    "Наприклад: /deleteGroupSub 16 \n\n" +
                    "Я підготував список всіх груп на котрі ти підписаний ) \n\n" +
                    "ім'я групи - ID групи \n\n" +
                    "%s";

        }
        String userGroupSubData = groupSubs.stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        sendBotMessageService.sendMessage(chatId, String.format(message, userGroupSubData));
    }
}