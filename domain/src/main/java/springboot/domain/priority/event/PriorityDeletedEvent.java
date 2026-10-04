package springboot.domain.priority.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.priority.model.valueobject.PriorityId;

public record PriorityDeletedEvent(
        PriorityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PriorityDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
