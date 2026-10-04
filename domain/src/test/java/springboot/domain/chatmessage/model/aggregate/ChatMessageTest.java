package springboot.domain.chatmessage.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.chatmessage.event.ChatMessageRegisteredEvent;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;
import springboot.domain.messagetype.model.valueobject.MessageTypeId;

class ChatMessageTest {
    @Test void shouldRegisterCreatedEvent() {
        ChatMessage aggregate = ChatMessage.register(
                ChatConversationId.generate(),
                MessageTypeId.generate(),
                ChatParticipantId.generate(),
                "{\"text\":\"Hello\"}",
                "{}");
        assertEquals(1, aggregate.domainEvents().size());
        ChatMessageRegisteredEvent event = assertInstanceOf(ChatMessageRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
