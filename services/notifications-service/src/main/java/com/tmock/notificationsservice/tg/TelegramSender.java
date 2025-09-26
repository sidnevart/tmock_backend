package com.tmock.notificationsservice.tg;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class TelegramSender {

    // TODO: потом вынесем в конфиг/секрет
    private static final String TOKEN = "8267090915:AAEf-BaXzA2Tc5m2Yf5W5kAfcjPI-WcKcNQ";
    private static final String API_URL = "https://api.telegram.org/bot" + TOKEN + "/sendMessage";

    private final RestTemplate rest = new RestTemplate();

    public void sendMessage(Long chatId, String text) {
        Map<String, Object> body = Map.of(
                "chat_id", chatId,
                "text", text
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<String> resp = rest.postForEntity(API_URL, request, String.class);
            if (!resp.getStatusCode().is2xxSuccessful()) {
                throw new IllegalStateException("Telegram API error: " + resp.getStatusCode() + " " + resp.getBody());
            }
        } catch (HttpStatusCodeException ex) {
            throw new IllegalStateException("Telegram API error: " + ex.getStatusCode() + " " + ex.getResponseBodyAsString(), ex);
        }
    }
}
