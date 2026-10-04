package springboot.domain.escalationstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record EscalationStatusDeletedEvent(
        EscalationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public EscalationStatusDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
