package com.tmock.authservice.controller;

import com.tmock.authservice.client.UserClient;
import com.tmock.authservice.dto.LoginRequest;
import com.tmock.authservice.dto.RegisterRequest;
import com.tmock.authservice.kafka.AuthEventsProducer;
import com.tmock.authservice.security.JwtService;
import com.tmock.authservice.dto.TelegramLoginRequest;
import com.tmock.authservice.dto.TokenResponse;
import com.tmock.authservice.security.TokenService;
import com.tmock.authservice.service.CredentialService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final CredentialService credentialService;
    private final UserClient userClient;
    private final TokenService tokenService;

    @PostMapping("/login/telegram")
    public ResponseEntity<TokenResponse> telegramLogin(@RequestBody TelegramLoginRequest req) {
        if (req.telegramId() == null) return ResponseEntity.badRequest().build();
        var user = userClient.byTelegram(req.telegramId());
        if (user == null) return ResponseEntity.status(404).build();

        long ttl = 3600;
        var token = tokenService.issueAccessToken(
                user.userId(), ttl,
                Map.of("telegramId", req.telegramId(), "username", req.username())
        );
        producer.publishLogin(user.userId(), req.telegramId());
        return ResponseEntity.ok(new TokenResponse(token, ttl));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        var cred = credentialService.verify(req.login(), req.password());
        var user = userClient.byId(cred.getUserId());
        if (user == null) return ResponseEntity.badRequest().build();
        var tgId = user.telegramId();
        var ttl = 3600;
        var token = jwt.generate(cred.getUserId(), Map.of("login", req.login(), "telegramId", tgId), ttl);
        producer.publishLogin(cred.getUserId(), tgId);
        return ResponseEntity.ok(new TokenResponse(token, ttl));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        var userId = userClient.byTelegram(req.telegramId()).userId();
        if  (userId == null) return ResponseEntity.status(404).build();
        credentialService.register(userId, req.login(), req.password());
        return ResponseEntity.ok().build();
    }
}


