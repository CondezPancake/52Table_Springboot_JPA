package springboot.domain.emailcontact.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.emailcontact.event.EmailContactRegisteredEvent;
import springboot.domain.contact.model.valueobject.ContactId;

class EmailContactTest {
    @Test void shouldRegisterCreatedEvent() {
        EmailContact aggregate = EmailContact.register(
                ContactId.generate(),
                "emergency@example.com",
                "Preferred email");
        assertEquals(1, aggregate.domainEvents().size());
        EmailContactRegisteredEvent event = assertInstanceOf(EmailContactRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
