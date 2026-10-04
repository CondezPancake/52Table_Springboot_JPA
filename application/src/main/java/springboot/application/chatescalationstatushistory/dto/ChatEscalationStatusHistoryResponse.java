package springboot.application.chatescalationstatushistory.dto;

import java.time.LocalDateTime;

import java.util.UUID;

public record ChatEscalationStatusHistoryResponse(
        UUID id,
        UUID escalationId,
        UUID escalationStatusId,
        LocalDateTime changedAt,
        LocalDateTime createdAt
) {
}
