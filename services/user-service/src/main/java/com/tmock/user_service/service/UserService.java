package com.tmock.user_service.service;

import com.tmock.user_service.exception.UserNotFoundException;
import com.tmock.user_service.repository.UserRepository;
import com.tmock.user_service.request.UserRequest;
import com.tmock.user_service.response.UserResponse;
import com.tmock.user_service.user.User;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public String createUser(UserRequest userRequest) {
        var user = userRepository.save(userMapper.toUser(userRequest));
        return user.getId();
    }


    public void updateUser(UserRequest userRequest) {
        var user = userRepository.findById(userRequest.id())
                .orElseThrow(()->{
                    new UserNotFoundException(String.format("User with id %s not found", userRequest.id()));
                    return null;
                });
        margeUser(user, userRequest);
        userRepository.save(user);
    }

    private void margeUser(User user, UserRequest userRequest) {
        if (StringUtils.isNotBlank(userRequest.username())) {
            user.setUsername(userRequest.username());
        }

        if (StringUtils.isNotBlank(userRequest.telegram_username())){
            user.setUsername(userRequest.username());
        }

    }

    public List<UserResponse> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::fromUser)
                .collect(Collectors.toList());
    }

    public UserResponse findById(String userId) {
        return userRepository.findById(userId)
                .map(userMapper::fromUser)
                .orElseThrow(()->{
                    new UserNotFoundException(String.format("User with id %s not found", userId));
                    return null;
                });
    }


    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
