package springboot.domain.messagetype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;

public record MessageTypeDeletedEvent(
        MessageTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public MessageTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
