package springboot.application.aimodel.dto;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

public record AiModelResponse(
        UUID id,
        UUID providerModelId,
        String nameModel,
        String modelKey,
        BigDecimal inputTokenPrice,
        BigDecimal outputTokenPrice,
        int maxTokens,
        int contextWindow,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
