package springboot.domain.phonecontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.phonecontact.model.valueobject.PhoneContactId;

public record PhoneContactDeletedEvent(
        PhoneContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PhoneContactDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
