package springboot.domain.airunstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;


public record AiRunStatusUpdatedEvent(
        AiRunStatusId id,
        String nameStatus,
        LocalDateTime occurredOn
) implements DomainEvent {
    public AiRunStatusUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameStatus, "nameStatus must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
