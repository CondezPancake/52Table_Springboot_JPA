package springboot.domain.emailcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.emailcontact.model.valueobject.EmailContactId;
import springboot.domain.contact.model.valueobject.ContactId;

public record EmailContactUpdatedEvent(
        EmailContactId id,
        ContactId contactId,
        String email,
        String notes,
        LocalDateTime occurredOn
) implements DomainEvent {
    public EmailContactUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(contactId, "contactId must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(notes, "notes must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
