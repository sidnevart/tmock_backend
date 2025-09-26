package com.tmock.authservice.dto;

public record RegisterRequest(
        Long telegramId,
        String login,
        String password
) {}