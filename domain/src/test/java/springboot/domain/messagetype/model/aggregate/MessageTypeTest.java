package springboot.domain.messagetype.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.messagetype.event.MessageTypeRegisteredEvent;


class MessageTypeTest {
    @Test void shouldRegisterCreatedEvent() {
        MessageType aggregate = MessageType.register(
                "Text");
        assertEquals(1, aggregate.domainEvents().size());
        MessageTypeRegisteredEvent event = assertInstanceOf(MessageTypeRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
