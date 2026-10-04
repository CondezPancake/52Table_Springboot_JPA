package springboot.application.consenttype.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ConsentTypeResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        String description,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
