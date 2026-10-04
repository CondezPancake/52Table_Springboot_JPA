package springboot.domain.providermodelai.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.providermodelai.event.ProviderModelAiRegisteredEvent;


class ProviderModelAiTest {
    @Test void shouldRegisterCreatedEvent() {
        ProviderModelAi aggregate = ProviderModelAi.register(
                "OpenAI",
                "AI Provider Inc.",
                "https://example.com",
                true);
        assertEquals(1, aggregate.domainEvents().size());
        ProviderModelAiRegisteredEvent event = assertInstanceOf(ProviderModelAiRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
