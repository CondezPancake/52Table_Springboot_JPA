package springboot.application.conversationstatus.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ConversationStatusResponse(
        UUID id,
        String nameStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
