package com.tmock.user_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProducer {
    private final KafkaTemplate<String, UserCreationConfirmation> kafkaTemplate;

    public void sendUserCreationConfirmation(UserCreationConfirmation userCreationConfirmation) {
        log.info("Sending user creation confirmation");
        Message<UserCreationConfirmation> msg = MessageBuilder
                .withPayload(userCreationConfirmation)
                .setHeader(TOPIC, "user-created")
                .build();
        kafkaTemplate.send(msg);
    }
}
