package springboot.application.encounterstatus.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record EncounterStatusResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
