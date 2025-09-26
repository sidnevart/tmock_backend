package com.tmock.user_service.controller;

import com.tmock.user_service.dto.RegisterTelegramDto;
import com.tmock.user_service.kafka.UserCreationConfirmation;
import com.tmock.user_service.kafka.UserProducer;
import com.tmock.user_service.model.User;
import com.tmock.user_service.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;


@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final UserProducer userProducer;

    @PostMapping("/register-telegram")
    public User register(@RequestBody RegisterTelegramDto dto){

        var saved = userRepository.findByTelegramId(dto.telegramId()).orElseGet(() ->
                userRepository.save(
                        User.builder()
                                .telegramId(dto.telegramId())
                                .username(dto.username())
                                .firstName(dto.firstName())
                                .lastName(dto.lastName())
                                .build()
                )
        );
        userProducer.sendUserCreationConfirmation(new UserCreationConfirmation(saved.getTelegramId(), saved.getUsername(), saved.getFirstName(), saved.getLastName()));
        return saved;
    }

    @GetMapping("/{telegramId}")
    public ResponseEntity<User> getUser(@PathVariable("telegramId") Long telegramId){
        return userRepository.findByTelegramId(telegramId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }


    @GetMapping("/by-telegram/{telegramId}")
    public ResponseEntity<User> getByTelegram(@PathVariable Long telegramId) {
        return userRepository.findByTelegramId(telegramId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/by_id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String id){
        return userRepository.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
