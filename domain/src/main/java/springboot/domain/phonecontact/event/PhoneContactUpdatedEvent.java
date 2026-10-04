package springboot.domain.phonecontact.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.phonecontact.model.valueobject.PhoneContactId;
import springboot.domain.contact.model.valueobject.ContactId;

public record PhoneContactUpdatedEvent(
        PhoneContactId id,
        ContactId contactId,
        String phone,
        String notes,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PhoneContactUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(contactId, "contactId must not be null");
        Objects.requireNonNull(notes, "notes must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
