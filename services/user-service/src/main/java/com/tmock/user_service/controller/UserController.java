package com.tmock.user_service.controller;

import com.tmock.user_service.request.UserRequest;
import com.tmock.user_service.response.UserResponse;
import com.tmock.user_service.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid UserRequest request) {
        return ResponseEntity.ok(service.createUser(request));
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody @Valid UserRequest userRequest) {
        service.updateUser(userRequest);
        return ResponseEntity.accepted().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/exits/{user-id}")
    public ResponseEntity<UserResponse> exists(@PathVariable("user-id") String userId) {
        return ResponseEntity.ok(service.findById(userId));
    }

    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(@PathVariable("user-id") String userId) {
        service.deleteUser(userId);
        return ResponseEntity.accepted().build();
    }
}
