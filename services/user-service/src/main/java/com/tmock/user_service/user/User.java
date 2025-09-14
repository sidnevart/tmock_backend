package com.tmock.user_service.user;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class User {

    @Id
    private String id;

    private String username;
    private String telegram_username;

    private String password;
    private LocalDateTime createdAt;
    private boolean isAdmin;
}
