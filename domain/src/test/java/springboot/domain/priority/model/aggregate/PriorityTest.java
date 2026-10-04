package springboot.domain.priority.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.priority.event.PriorityRegisteredEvent;


class PriorityTest {
    @Test void shouldRegisterCreatedEvent() {
        Priority aggregate = Priority.register(
                "High");
        assertEquals(1, aggregate.domainEvents().size());
        PriorityRegisteredEvent event = assertInstanceOf(PriorityRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
