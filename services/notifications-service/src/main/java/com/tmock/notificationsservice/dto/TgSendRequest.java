package com.tmock.notificationsservice.dto;

public record TgSendRequest (
        @jakarta.validation.constraints.NotNull Long chatId,
        @jakarta.validation.constraints.NotBlank String text
) {
}
