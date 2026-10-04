package springboot.domain.medicationroute.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.medicationroute.event.MedicationRouteRegisteredEvent;


class MedicationRouteTest {
    @Test void shouldRegisterCreatedEvent() {
        MedicationRoute aggregate = MedicationRoute.register(
                "ORAL",
                "Oral",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        MedicationRouteRegisteredEvent event = assertInstanceOf(MedicationRouteRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
