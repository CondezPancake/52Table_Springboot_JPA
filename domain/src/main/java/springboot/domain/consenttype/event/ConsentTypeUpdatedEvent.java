package springboot.domain.consenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.consenttype.model.valueobject.ConsentTypeId;


public record ConsentTypeUpdatedEvent(
        ConsentTypeId id,
        String code,
        String name,
        boolean active,
        String description,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ConsentTypeUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
