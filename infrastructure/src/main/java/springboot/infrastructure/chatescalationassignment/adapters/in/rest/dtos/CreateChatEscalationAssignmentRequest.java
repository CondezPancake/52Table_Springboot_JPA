package springboot.infrastructure.chatescalationassignment.adapters.in.rest.dtos;

import java.util.UUID;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateChatEscalationAssignmentRequest(
        @NotNull(message = "escalationId is required")
        UUID escalationId,

        @NotNull(message = "professionalId is required")
        UUID professionalId,

        @NotNull(message = "assignedAt is required")
        LocalDateTime assignedAt
) {
}
