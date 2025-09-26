package com.tmock.authservice.service;
import com.tmock.authservice.model.Credential;
import com.tmock.authservice.repository.CredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CredentialService {
    private final CredentialRepository repo;
    private final PasswordEncoder encoder;

    public void register(String id, String login, String rawPassword) {
        if (repo.existsByLogin(login)) throw new IllegalStateException("login already exists");
        if (repo.existsByLogin(login)) throw new IllegalStateException("user already has credentials");

        var now = Instant.now();
        var cred = Credential.builder()
                .userId(id)
                .login(login)
                .passwordHash(encoder.encode(rawPassword))
                .createdAt(now).updatedAt(now)
                .build();
        repo.save(cred);
    }

    public Credential verify(String login, String rawPassword) {
        var cred = repo.findByLogin(login).orElseThrow(() -> new IllegalArgumentException("bad credentials"));
        if (!encoder.matches(rawPassword, cred.getPasswordHash()))
            throw new IllegalArgumentException("bad credentials");
        return cred;
    }
}
