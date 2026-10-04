package springboot.domain.phonecontact.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.phonecontact.event.PhoneContactRegisteredEvent;
import springboot.domain.contact.model.valueobject.ContactId;

class PhoneContactTest {
    @Test void shouldRegisterCreatedEvent() {
        PhoneContact aggregate = PhoneContact.register(
                ContactId.generate(),
                null,
                "Call after 5 PM");
        assertEquals(1, aggregate.domainEvents().size());
        PhoneContactRegisteredEvent event = assertInstanceOf(PhoneContactRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
