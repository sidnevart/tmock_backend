package com.tmock.authservice.kafka;

import java.time.Instant;

public record AuthLoginEvent (String id, Long telegramId, String occuredAt) {
}
