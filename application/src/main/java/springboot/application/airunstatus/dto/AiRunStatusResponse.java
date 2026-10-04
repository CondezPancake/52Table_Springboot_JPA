package springboot.application.airunstatus.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record AiRunStatusResponse(
        UUID id,
        String nameStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
