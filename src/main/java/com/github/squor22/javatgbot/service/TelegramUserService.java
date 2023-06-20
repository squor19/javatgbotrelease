package com.github.squor22.javatgbot.service;

import com.github.squor22.javatgbot.entity.TelegramUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TelegramUserService {

    void save(TelegramUser telegramUser);
    Optional<TelegramUser> findByChatId(Long chatId);
    List<TelegramUser> findAllInActiveUsers();
    List<TelegramUser> findAllActiveUsers();

}