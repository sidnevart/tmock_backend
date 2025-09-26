package com.tmock.authservice.client;

import com.tmock.authservice.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "user-service", path = "/api/users")
public interface UserClient {
    @GetMapping("/by-telegram/{telegramId}")
    UserDTO byTelegram(@PathVariable("telegramId") Long telegramId);

    @GetMapping("/by_id/{id}")
    UserDTO byId(@PathVariable("id") String id);
}
