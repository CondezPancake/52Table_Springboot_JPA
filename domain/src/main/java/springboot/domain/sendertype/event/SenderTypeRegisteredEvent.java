package springboot.domain.sendertype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;

public record SenderTypeRegisteredEvent(
        SenderTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public SenderTypeRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
