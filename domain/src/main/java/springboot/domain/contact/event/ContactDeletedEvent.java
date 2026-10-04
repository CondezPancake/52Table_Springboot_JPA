package springboot.domain.contact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.contact.model.valueobject.ContactId;

public record ContactDeletedEvent(
        ContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ContactDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
