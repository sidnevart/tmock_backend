package com.tmock.authservice.repository;

import com.tmock.authservice.model.Credential;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CredentialRepository extends MongoRepository<Credential, String> {
    Optional<Credential> findByLogin(String login);
    boolean existsByLogin(String login);
    boolean existsByUserId(String userId);
}
