package springboot.domain.airunstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.airunstatus.event.AiRunStatusRegisteredEvent;


class AiRunStatusTest {
    @Test void shouldRegisterCreatedEvent() {
        AiRunStatus aggregate = AiRunStatus.register(
                "Completed");
        assertEquals(1, aggregate.domainEvents().size());
        AiRunStatusRegisteredEvent event = assertInstanceOf(AiRunStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
