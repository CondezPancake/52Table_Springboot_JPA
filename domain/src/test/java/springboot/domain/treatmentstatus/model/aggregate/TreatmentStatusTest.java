package springboot.domain.treatmentstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.treatmentstatus.event.TreatmentStatusRegisteredEvent;


class TreatmentStatusTest {
    @Test void shouldRegisterCreatedEvent() {
        TreatmentStatus aggregate = TreatmentStatus.register(
                "ACTIVE",
                "Active",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        TreatmentStatusRegisteredEvent event = assertInstanceOf(TreatmentStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
