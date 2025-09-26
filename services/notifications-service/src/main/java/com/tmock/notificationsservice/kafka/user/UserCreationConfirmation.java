package com.tmock.notificationsservice.kafka.user;

public record UserCreationConfirmation(
        Long telegramId,
        String username,
        String firstName,
        String lastName
) {
}
