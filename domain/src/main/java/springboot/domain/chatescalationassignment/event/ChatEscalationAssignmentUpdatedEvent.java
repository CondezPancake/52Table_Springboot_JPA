package springboot.domain.chatescalationassignment.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record ChatEscalationAssignmentUpdatedEvent(
        ChatEscalationAssignmentId id,
        ChatEscalationId escalationId,
        ProfessionalId professionalId,
        LocalDateTime assignedAt,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatEscalationAssignmentUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(escalationId, "escalationId must not be null");
        Objects.requireNonNull(professionalId, "professionalId must not be null");
        Objects.requireNonNull(assignedAt, "assignedAt must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
