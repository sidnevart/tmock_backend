package com.tmock.authservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
@Document("credentials")
public class Credential {
    @Id private String id;                 // mongo _id
    @Indexed(unique = true) private String userId;   // UUID из user-service
    @Indexed(unique = true) private String login;    // email или username (на твой выбор)
    private String passwordHash;                    // BCrypt
    private Instant createdAt;
    private Instant updatedAt;
}