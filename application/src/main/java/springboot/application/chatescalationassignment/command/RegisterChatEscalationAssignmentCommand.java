package springboot.application.chatescalationassignment.command;

import java.util.Objects;
import java.time.LocalDateTime;

import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record RegisterChatEscalationAssignmentCommand(
        ChatEscalationId escalationId,
        ProfessionalId professionalId,
        LocalDateTime assignedAt
) {
    public RegisterChatEscalationAssignmentCommand {
        Objects.requireNonNull(escalationId, "escalationId must not be null");
        Objects.requireNonNull(professionalId, "professionalId must not be null");
        Objects.requireNonNull(assignedAt, "assignedAt must not be null");
    }
}
