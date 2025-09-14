package com.tmock.user_service.response;

import java.time.LocalDateTime;

public record UserResponse (
        String id,
        String username,

        String telegram_username,

        LocalDateTime createdAt,
        boolean isAdmin
) {
}
