// com.tmock.notificationsservice.kafka.KafkaAuthLoginFactory.java
package com.tmock.notificationsservice.kafka;

import com.tmock.notificationsservice.kafka.auth.AuthLoginEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaAuthLoginFactory {

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AuthLoginEvent> authLoginFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "notifications-service-auth-" + UUID.randomUUID());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        var value = new JsonDeserializer<>(AuthLoginEvent.class, false);
        value.addTrustedPackages("*");

        var cf = new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), value);
        var factory = new ConcurrentKafkaListenerContainerFactory<String, AuthLoginEvent>();
        factory.setConsumerFactory(cf);
        return factory;
    }
}
