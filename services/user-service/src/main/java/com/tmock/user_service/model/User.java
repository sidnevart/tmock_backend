package com.tmock.user_service.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

import java.time.Instant;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
@Document("users")
public class User {

    @Id
    private String id;

    @Indexed(unique = true, sparse = true)
    private Long telegramId;

    @Indexed(unique = true, sparse = true)
    private String username;

    private String firstName;
    private String lastName;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;


}
