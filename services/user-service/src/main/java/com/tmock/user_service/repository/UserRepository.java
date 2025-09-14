package com.tmock.user_service.repository;

import com.tmock.user_service.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
