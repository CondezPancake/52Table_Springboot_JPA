package springboot.application.chatmessage.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ChatMessageResponse(
        UUID id,
        UUID conversationId,
        UUID messageTypeId,
        UUID participantId,
        String content,
        String metadata,
        LocalDateTime createdAt
) {
}
