package com.tmock.problemservice.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Document("problems")
public class Problem {
    @Id
    private String id;

    private String title;

    private String difficulty;

    private List<String> topics;

    private String statementUri;

    @CreatedDate
    private Instant createdAt;


}

