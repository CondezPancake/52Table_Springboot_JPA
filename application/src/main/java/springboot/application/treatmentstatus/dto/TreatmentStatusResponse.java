package springboot.application.treatmentstatus.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record TreatmentStatusResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
