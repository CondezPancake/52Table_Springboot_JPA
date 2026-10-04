package springboot.domain.chatescalationassignment.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.chatescalationassignment.event.ChatEscalationAssignmentRegisteredEvent;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

class ChatEscalationAssignmentTest {
    @Test void shouldRegisterCreatedEvent() {
        ChatEscalationAssignment aggregate = ChatEscalationAssignment.register(
                ChatEscalationId.generate(),
                ProfessionalId.generate(),
                java.time.LocalDateTime.of(2026, 1, 10, 10, 0));
        assertEquals(1, aggregate.domainEvents().size());
        ChatEscalationAssignmentRegisteredEvent event = assertInstanceOf(ChatEscalationAssignmentRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
