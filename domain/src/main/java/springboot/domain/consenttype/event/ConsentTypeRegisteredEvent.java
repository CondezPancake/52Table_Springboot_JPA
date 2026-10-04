package springboot.domain.consenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.consenttype.model.valueobject.ConsentTypeId;

public record ConsentTypeRegisteredEvent(
        ConsentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ConsentTypeRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
