package springboot.domain.consenttype.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.consenttype.event.ConsentTypeRegisteredEvent;


class ConsentTypeTest {
    @Test void shouldRegisterCreatedEvent() {
        ConsentType aggregate = ConsentType.register(
                "TREATMENT",
                "Treatment consent",
                true,
                "Consent for treatment");
        assertEquals(1, aggregate.domainEvents().size());
        ConsentTypeRegisteredEvent event = assertInstanceOf(ConsentTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
