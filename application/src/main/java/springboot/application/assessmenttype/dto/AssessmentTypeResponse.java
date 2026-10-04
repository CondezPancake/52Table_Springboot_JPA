package springboot.application.assessmenttype.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record AssessmentTypeResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
