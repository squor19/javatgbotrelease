package com.github.squor22.javatgbot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@Data
@PropertySource("application.properties")
public class BotConfig {

    @Value("${bot.username}")
    String botName;

    @Value("${bot.token}")
    String botToken;

    @Value("#{'${bot.admins}'.split(',')}")
    List<String> admins;
}
