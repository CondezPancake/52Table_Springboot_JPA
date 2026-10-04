package springboot.application.aimodel.command;

import java.util.Objects;
import java.math.BigDecimal;

import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;

public record UpdateAiModelCommand(
        AiModelId id,
        ProviderModelAiId providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        int maxTokens,
        int contextWindow,
        boolean active
) {
    public UpdateAiModelCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(providerModelId, "providerModelId must not be null");
        Objects.requireNonNull(nameModel, "nameModel must not be null");
        Objects.requireNonNull(modelKey, "modelKey must not be null");
        Objects.requireNonNull(inputTokenPrice, "inputTokenPrice must not be null");
        Objects.requireNonNull(outputTokenPrice, "outputTokenPrice must not be null");
    }
}
