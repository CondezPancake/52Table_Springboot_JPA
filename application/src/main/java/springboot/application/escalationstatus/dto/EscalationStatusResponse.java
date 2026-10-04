package springboot.application.escalationstatus.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record EscalationStatusResponse(
        UUID id,
        String nameStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
