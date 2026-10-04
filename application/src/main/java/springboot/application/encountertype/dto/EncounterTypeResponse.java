package springboot.application.encountertype.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record EncounterTypeResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
