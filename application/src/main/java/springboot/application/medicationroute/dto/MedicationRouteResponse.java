package springboot.application.medicationroute.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record MedicationRouteResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
