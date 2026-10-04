package springboot.domain.chatconversationaisetting.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.chatconversationaisetting.event.ChatConversationAiSettingRegisteredEvent;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;

class ChatConversationAiSettingTest {
    @Test void shouldRegisterCreatedEvent() {
        ChatConversationAiSetting aggregate = ChatConversationAiSetting.register(
                ChatConversationId.generate(),
                true,
                AiModelId.generate());
        assertEquals(1, aggregate.domainEvents().size());
        ChatConversationAiSettingRegisteredEvent event = assertInstanceOf(ChatConversationAiSettingRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
