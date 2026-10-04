package springboot.application.treatmentgoalstatus.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record TreatmentGoalStatusResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
