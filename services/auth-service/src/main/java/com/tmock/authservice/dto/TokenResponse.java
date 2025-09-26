package com.tmock.authservice.dto;

public record TokenResponse(String accessToken, long expiresInSeconds) {}
