package springboot.domain.risklevel.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.risklevel.event.RiskLevelRegisteredEvent;


class RiskLevelTest {
    @Test void shouldRegisterCreatedEvent() {
        RiskLevel aggregate = RiskLevel.register(
                "HIGH",
                "High",
                true,
                3);
        assertEquals(1, aggregate.domainEvents().size());
        RiskLevelRegisteredEvent event = assertInstanceOf(RiskLevelRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
