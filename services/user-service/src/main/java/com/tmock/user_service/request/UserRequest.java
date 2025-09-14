package com.tmock.user_service.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record UserRequest(
        String id,
        @NotNull(message="User's username is required")
        String username,

        @NotNull(message="User's telegram username is required")
        String telegram_username,

        LocalDateTime createdAt,
        boolean isAdmin
) {

}
