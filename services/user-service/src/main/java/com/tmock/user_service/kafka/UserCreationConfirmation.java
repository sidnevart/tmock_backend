package com.tmock.user_service.kafka;

public record UserCreationConfirmation (
        Long telegramId,
        String username,
        String firstName,
        String lastName
) {
}
