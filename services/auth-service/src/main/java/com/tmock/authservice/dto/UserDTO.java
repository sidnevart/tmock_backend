package com.tmock.authservice.dto;

public record UserDTO (
        String userId,
        Long telegramId,
        String firstName,
        String lastName
) {

}
