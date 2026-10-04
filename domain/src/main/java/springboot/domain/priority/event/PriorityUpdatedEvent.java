package springboot.domain.priority.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.priority.model.valueobject.PriorityId;


public record PriorityUpdatedEvent(
        PriorityId id,
        String namePriority,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PriorityUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(namePriority, "namePriority must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
