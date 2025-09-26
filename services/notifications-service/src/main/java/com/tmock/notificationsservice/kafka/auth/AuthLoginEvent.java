package com.tmock.notificationsservice.kafka.auth;

import java.time.Instant;

public record AuthLoginEvent (String id, Long telegramId, String occuredAt) {
}
