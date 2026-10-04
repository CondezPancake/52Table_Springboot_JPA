package springboot.domain.escalationstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.escalationstatus.event.EscalationStatusRegisteredEvent;


class EscalationStatusTest {
    @Test void shouldRegisterCreatedEvent() {
        EscalationStatus aggregate = EscalationStatus.register(
                "Open");
        assertEquals(1, aggregate.domainEvents().size());
        EscalationStatusRegisteredEvent event = assertInstanceOf(EscalationStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
