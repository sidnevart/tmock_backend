package com.tmock.authservice.dto;


public record TelegramLoginRequest(
        String userId,
        Long telegramId,
        String username
) {}