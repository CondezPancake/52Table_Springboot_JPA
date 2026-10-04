package springboot.application.chatescalationassignment.dto;


import java.time.LocalDateTime;
import java.util.UUID;

public record ChatEscalationAssignmentResponse(
        UUID id,
        UUID escalationId,
        UUID professionalId,
        LocalDateTime assignedAt
) {
}
