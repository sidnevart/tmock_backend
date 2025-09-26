package com.tmock.notificationsservice.controller;

import com.tmock.notificationsservice.dto.TgSendRequest;
import com.tmock.notificationsservice.tg.TelegramSender;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationsServiceController {

    private final TelegramSender telegramSender;


    @PostMapping("/telegram/send")
    public ResponseEntity<Void> sendMessage(@Valid @RequestBody TgSendRequest req) {
        telegramSender.sendMessage(req.chatId(), req.text());
        return ResponseEntity.accepted().build();
    }
}
