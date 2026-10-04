package springboot.domain.chatconversation.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.chatconversation.event.ChatConversationRegisteredEvent;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.priority.model.valueobject.PriorityId;

class ChatConversationTest {
    @Test void shouldRegisterCreatedEvent() {
        ChatConversation aggregate = ChatConversation.register(
                ConversationStatusId.generate(),
                PriorityId.generate(),
                null,
                null,
                null,
                null);
        assertEquals(1, aggregate.domainEvents().size());
        ChatConversationRegisteredEvent event = assertInstanceOf(ChatConversationRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
