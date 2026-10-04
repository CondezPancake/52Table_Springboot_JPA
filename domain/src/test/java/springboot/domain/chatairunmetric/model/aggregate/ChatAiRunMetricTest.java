package springboot.domain.chatairunmetric.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.chatairunmetric.event.ChatAiRunMetricRegisteredEvent;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

class ChatAiRunMetricTest {
    @Test void shouldRegisterCreatedEvent() {
        ChatAiRunMetric aggregate = ChatAiRunMetric.register(
                ChatAiRunId.generate(),
                100,
                50,
                150,
                new java.math.BigDecimal("0.001500"));
        assertEquals(1, aggregate.domainEvents().size());
        ChatAiRunMetricRegisteredEvent event = assertInstanceOf(ChatAiRunMetricRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
