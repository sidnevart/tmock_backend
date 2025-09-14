package com.tmock.user_service.service;

import com.tmock.user_service.repository.UserRepository;
import com.tmock.user_service.request.UserRequest;
import com.tmock.user_service.response.UserResponse;
import com.tmock.user_service.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    private final UserRepository userRepository;

    public UserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User toUser(UserRequest userRequest) {
        if (userRequest == null) {
            return null;
        }
        return User.builder()
                .id(userRequest.id())
                .username(userRequest.username())
                .telegram_username(userRequest.telegram_username())
                .createdAt(userRequest.createdAt())
                .build();
    }

    public UserResponse fromUser(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getTelegram_username(), user.getCreatedAt(), user.isAdmin());
    }
}
