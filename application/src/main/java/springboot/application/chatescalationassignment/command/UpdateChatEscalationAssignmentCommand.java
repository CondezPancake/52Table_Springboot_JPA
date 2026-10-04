package springboot.application.chatescalationassignment.command;

import java.util.Objects;
import java.time.LocalDateTime;

import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record UpdateChatEscalationAssignmentCommand(
        ChatEscalationAssignmentId id,
        ChatEscalationId escalationId,
        ProfessionalId professionalId,
        LocalDateTime assignedAt
) {
    public UpdateChatEscalationAssignmentCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(escalationId, "escalationId must not be null");
        Objects.requireNonNull(professionalId, "professionalId must not be null");
        Objects.requireNonNull(assignedAt, "assignedAt must not be null");
    }
}
