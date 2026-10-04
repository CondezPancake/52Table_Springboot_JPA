package springboot.application.chatconversationaisetting.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ChatConversationAiSettingResponse(
        UUID id,
        UUID conversationId,
        boolean aiEnabled,
        UUID defaultModelId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
