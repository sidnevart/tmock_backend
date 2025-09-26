package com.tmock.user_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.tmock.user_service.model.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByTelegramId(Long telegramId);
}
