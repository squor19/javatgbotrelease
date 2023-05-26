package com.github.squor22.javatgbot.command;

import com.github.squor22.javatgbot.bot.JavaTgBot;
import com.github.squor22.javatgbot.bot.command.Command;
import com.github.squor22.javatgbot.bot.service.SendBotMessageService;
import com.github.squor22.javatgbot.bot.service.SendBotMessageServiceImpl;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

abstract class AbstractCommandTest {

    protected JavaTgBot javaTgBot = Mockito.mock(JavaTgBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(javaTgBot);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract Command getCommand();

    public void shouldProperExecuteCommand() throws TelegramApiException {
        //given

        Long chatId = 1234567824356L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);


        //when
        getCommand().execute(update);

        //then
        Mockito.verify(javaTgBot).execute(sendMessage);
    }
}
