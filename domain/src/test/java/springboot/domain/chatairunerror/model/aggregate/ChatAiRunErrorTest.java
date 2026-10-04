package springboot.domain.chatairunerror.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.chatairunerror.event.ChatAiRunErrorRegisteredEvent;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

class ChatAiRunErrorTest {
    @Test void shouldRegisterCreatedEvent() {
        ChatAiRunError aggregate = ChatAiRunError.register(
                ChatAiRunId.generate(),
                "Provider request failed",
                "PROVIDER_ERROR",
                "provider-error-001");
        assertEquals(1, aggregate.domainEvents().size());
        ChatAiRunErrorRegisteredEvent event = assertInstanceOf(ChatAiRunErrorRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
