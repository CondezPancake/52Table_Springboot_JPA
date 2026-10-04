package springboot.domain.aimodel.model.aggregate;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import springboot.domain.aimodel.event.AiModelRegisteredEvent;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;

class AiModelTest {
    @Test void shouldRegisterCreatedEvent() {
        AiModel aggregate = AiModel.register(
                ProviderModelAiId.generate(),
                "Model One",
                "model-one",
                new java.math.BigDecimal("0.00000100"),
                new java.math.BigDecimal("0.00000200"),
                4096,
                128000,
                true);
        assertEquals(1, aggregate.domainEvents().size());
        AiModelRegisteredEvent event = assertInstanceOf(AiModelRegisteredEvent.class, aggregate.domainEvents().getFirst());
        assertEquals(aggregate.id(), event.id());
        assertNotNull(event.occurredOn());
    }
}
