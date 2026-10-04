package springboot.domain.sendertype.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.sendertype.event.SenderTypeRegisteredEvent;


class SenderTypeTest {
    @Test void shouldRegisterCreatedEvent() {
        SenderType aggregate = SenderType.register(
                "Patient");
        assertEquals(1, aggregate.domainEvents().size());
        SenderTypeRegisteredEvent event = assertInstanceOf(SenderTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
