package springboot.domain.chatparticipant.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.chatparticipant.event.ChatParticipantRegisteredEvent;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.sendertype.model.valueobject.SenderTypeId;

class ChatParticipantTest {
    @Test void shouldRegisterCreatedEvent() {
        ChatParticipant aggregate = ChatParticipant.register(
                ChatConversationId.generate(),
                SenderTypeId.generate(),
                null,
                null);
        assertEquals(1, aggregate.domainEvents().size());
        ChatParticipantRegisteredEvent event = assertInstanceOf(ChatParticipantRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
