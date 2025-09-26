package com.tmock.user_service.dto;

public record RegisterTelegramDto(
        Long telegramId,
        String username,
        String firstName,
        String lastName
) {
}
