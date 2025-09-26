package com.tmock.problemservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateProblemRequest(
        @NotBlank String title,
        @NotBlank String difficulty,
        @NotNull List<String> topics,
        String statementUri
) {
}
