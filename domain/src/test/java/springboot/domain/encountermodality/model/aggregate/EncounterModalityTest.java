package springboot.domain.encountermodality.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.encountermodality.event.EncounterModalityRegisteredEvent;


class EncounterModalityTest {
    @Test void shouldRegisterCreatedEvent() {
        EncounterModality aggregate = EncounterModality.register(
                "IN_PERSON",
                "In person",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        EncounterModalityRegisteredEvent event = assertInstanceOf(EncounterModalityRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
