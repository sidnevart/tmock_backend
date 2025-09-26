package com.tmock.problemservice.dto;

import java.util.List;

public record UpdateProblemRequest(
        String title,
        String difficulty,
        List<String> topics,
        String statementUri
) {}