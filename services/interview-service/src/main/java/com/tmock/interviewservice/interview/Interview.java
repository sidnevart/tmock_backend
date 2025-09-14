package com.tmock.interviewservice.interview;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Interview {
    @Id
    @GeneratedValue
    private Integer interviewId;

    @NotNull
    private String candidateId;

    private String interviewerId;

    private LocalDateTime createdAt;
    private LocalDateTime endedAt;

    private boolean isAlone;


}
