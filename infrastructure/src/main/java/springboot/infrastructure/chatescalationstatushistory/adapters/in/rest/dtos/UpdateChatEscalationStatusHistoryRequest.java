package springboot.infrastructure.chatescalationstatushistory.adapters.in.rest.dtos;

import java.util.UUID;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateChatEscalationStatusHistoryRequest(
        @NotNull(message = "escalationId is required")
        UUID escalationId,

        @NotNull(message = "escalationStatusId is required")
        UUID escalationStatusId,

        @NotNull(message = "changedAt is required")
        LocalDateTime changedAt
) {
}
