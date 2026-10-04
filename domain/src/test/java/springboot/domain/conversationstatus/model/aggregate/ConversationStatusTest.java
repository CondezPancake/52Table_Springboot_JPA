package springboot.domain.conversationstatus.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.conversationstatus.event.ConversationStatusRegisteredEvent;


class ConversationStatusTest {
    @Test void shouldRegisterCreatedEvent() {
        ConversationStatus aggregate = ConversationStatus.register(
                "Open");
        assertEquals(1, aggregate.domainEvents().size());
        ConversationStatusRegisteredEvent event = assertInstanceOf(ConversationStatusRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
