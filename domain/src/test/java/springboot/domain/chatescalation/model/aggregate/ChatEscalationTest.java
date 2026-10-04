package springboot.domain.chatescalation.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.chatescalation.event.ChatEscalationRegisteredEvent;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

class ChatEscalationTest {
    @Test void shouldRegisterCreatedEvent() {
        ChatEscalation aggregate = ChatEscalation.register(
                ChatConversationId.generate(),
                EscalationStatusId.generate(),
                true,
                "Requires professional review");
        assertEquals(1, aggregate.domainEvents().size());
        ChatEscalationRegisteredEvent event = assertInstanceOf(ChatEscalationRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
