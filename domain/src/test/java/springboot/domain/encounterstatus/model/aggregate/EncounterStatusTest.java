package springboot.domain.encounterstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.encounterstatus.event.EncounterStatusRegisteredEvent;


class EncounterStatusTest {
    @Test void shouldRegisterCreatedEvent() {
        EncounterStatus aggregate = EncounterStatus.register(
                "SCHEDULED",
                "Scheduled",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        EncounterStatusRegisteredEvent event = assertInstanceOf(EncounterStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
