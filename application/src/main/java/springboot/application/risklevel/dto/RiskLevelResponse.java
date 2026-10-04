package springboot.application.risklevel.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record RiskLevelResponse(
        UUID id,
        String code,
        String name,
        boolean active,
        int severity,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
