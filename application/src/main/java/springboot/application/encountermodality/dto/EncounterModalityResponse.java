package springboot.application.encountermodality.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record EncounterModalityResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
