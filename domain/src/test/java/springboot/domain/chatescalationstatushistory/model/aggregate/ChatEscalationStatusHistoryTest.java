package springboot.domain.chatescalationstatushistory.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryRegisteredEvent;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

class ChatEscalationStatusHistoryTest {
    @Test void shouldRegisterCreatedEvent() {
        ChatEscalationStatusHistory aggregate = ChatEscalationStatusHistory.register(
                ChatEscalationId.generate(),
                EscalationStatusId.generate(),
                java.time.LocalDateTime.of(2026, 1, 10, 10, 30));
        assertEquals(1, aggregate.domainEvents().size());
        ChatEscalationStatusHistoryRegisteredEvent event = assertInstanceOf(ChatEscalationStatusHistoryRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
