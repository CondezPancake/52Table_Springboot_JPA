package springboot.domain.aimodel.event;

import java.time.LocalDateTime;
import java.util.Objects;
import java.math.BigDecimal;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;

public record AiModelUpdatedEvent(
        AiModelId id,
        ProviderModelAiId providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        int maxTokens,
        int contextWindow,
        boolean active,
        LocalDateTime occurredOn
) implements DomainEvent {
    public AiModelUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(providerModelId, "providerModelId must not be null");
        Objects.requireNonNull(nameModel, "nameModel must not be null");
        Objects.requireNonNull(modelKey, "modelKey must not be null");
        Objects.requireNonNull(inputTokenPrice, "inputTokenPrice must not be null");
        Objects.requireNonNull(outputTokenPrice, "outputTokenPrice must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
