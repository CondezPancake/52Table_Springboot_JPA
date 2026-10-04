package springboot.application.chatairun.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ChatAiRunResponse(
        UUID id,
        UUID conversationId,
        UUID messageId,
        UUID modelId,
        UUID aiRunStatusId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
