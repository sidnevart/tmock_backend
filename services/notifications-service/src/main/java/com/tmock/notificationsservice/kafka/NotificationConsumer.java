package com.tmock.notificationsservice.kafka;

import com.tmock.notificationsservice.kafka.auth.AuthLoginEvent;
import com.tmock.notificationsservice.kafka.user.UserCreationConfirmation;
import com.tmock.notificationsservice.tg.TelegramSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationConsumer {
    private final TelegramSender telegramSender;
    @KafkaListener(topics = "user-created")
    public void consumeUserCreationConfirmation(UserCreationConfirmation userCreationConfirmation) throws MessagingException {
        log.info("Received new user creation confirmation: {}", userCreationConfirmation);
        if (userCreationConfirmation != null){
            if (userCreationConfirmation.telegramId() != null) {
                telegramSender.sendMessage(userCreationConfirmation.telegramId(), format("Аккаунт создан! Добро пожаловать в CuMock, %s", userCreationConfirmation.firstName()));
            } else {
                log.info("Telegram id not found => cannot send user creation confirmation");
            }
        }
    }

    @KafkaListener(topics = "auth-login", containerFactory = "authLoginFactory")
    public void consumeAuthLogin(AuthLoginEvent authLoginEvent) {
        log.info("Received new auth login: {}", authLoginEvent);
        if (authLoginEvent.telegramId() != null) {
            telegramSender.sendMessage(authLoginEvent.telegramId(), "\uD83D\uDD10 Вход в аккаунт выполнен");
        } else {
            log.info("skip auth-login: telegramId is null for userId={}", authLoginEvent.id());
        }
    }
}
