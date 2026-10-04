package springboot.application.chatescalation.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ChatEscalationResponse(
        UUID id,
        UUID conversationId,
        UUID statusId,
        boolean fromAi,
        String reason,
        LocalDateTime createdAt
) {
}
