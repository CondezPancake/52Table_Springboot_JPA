package springboot.domain.priority.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.priority.model.valueobject.PriorityId;

public record PriorityRegisteredEvent(
        PriorityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PriorityRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
