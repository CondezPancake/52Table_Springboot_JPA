package springboot.domain.emailcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.emailcontact.model.valueobject.EmailContactId;

public record EmailContactRegisteredEvent(
        EmailContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public EmailContactRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
