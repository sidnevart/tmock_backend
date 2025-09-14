package com.tmock.user_service.handler;


import java.util.Map;

public record MistakeResponse (
        Map<String, String> errors
) {

}
