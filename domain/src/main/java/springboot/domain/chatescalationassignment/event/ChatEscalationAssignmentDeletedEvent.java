package springboot.domain.chatescalationassignment.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

public record ChatEscalationAssignmentDeletedEvent(
        ChatEscalationAssignmentId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatEscalationAssignmentDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
