package springboot.domain.chatairun.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.chatairun.event.ChatAiRunRegisteredEvent;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;

class ChatAiRunTest {
    @Test void shouldRegisterCreatedEvent() {
        ChatAiRun aggregate = ChatAiRun.register(
                ChatConversationId.generate(),
                ChatMessageId.generate(),
                AiModelId.generate(),
                AiRunStatusId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        ChatAiRunRegisteredEvent event = assertInstanceOf(ChatAiRunRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
