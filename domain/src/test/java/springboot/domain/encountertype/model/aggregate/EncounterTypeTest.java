package springboot.domain.encountertype.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.encountertype.event.EncounterTypeRegisteredEvent;


class EncounterTypeTest {
    @Test void shouldRegisterCreatedEvent() {
        EncounterType aggregate = EncounterType.register(
                "INITIAL",
                "Initial consultation",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        EncounterTypeRegisteredEvent event = assertInstanceOf(EncounterTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
