package com.tmock.authservice.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthEventsProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishLogin(String userId, Long telegramId) {
        var evt = new AuthLoginEvent(userId, telegramId, Instant.now().toString());
        kafkaTemplate.send("auth-login", userId, evt).whenComplete((m, ex) -> {
            if (ex != null) log.error("auth-login failed: {}", ex.toString(), ex);
            else log.info("auth-login sent key={} offset={}", userId, m.getRecordMetadata().offset());
        });
    }


}
