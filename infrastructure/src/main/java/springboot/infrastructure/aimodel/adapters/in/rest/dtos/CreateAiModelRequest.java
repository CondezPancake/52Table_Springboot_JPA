package springboot.infrastructure.aimodel.adapters.in.rest.dtos;

import java.util.UUID;

import java.math.BigDecimal;
import jakarta.validation.constraints.Digits;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAiModelRequest(
        @NotNull(message = "providerModelId is required")
        UUID providerModelId,

        @NotNull(message = "nameModel is required")
        @Size(max = 100, message = "nameModel must have at most 100 characters")
        String nameModel,

        @NotNull(message = "modelKey is required")
        @Size(max = 120, message = "modelKey must have at most 120 characters")
        String modelKey,

        @NotNull(message = "inputTokenPrice is required")
        @Digits(integer = 4, fraction = 8, message = "inputTokenPrice must fit DECIMAL(12,8)")
        BigDecimal inputTokenPrice,

        @NotNull(message = "outputTokenPrice is required")
        @Digits(integer = 4, fraction = 8, message = "outputTokenPrice must fit DECIMAL(12,8)")
        BigDecimal outputTokenPrice,

        @NotNull(message = "maxTokens is required")
        Integer maxTokens,

        @NotNull(message = "contextWindow is required")
        Integer contextWindow,

        @NotNull(message = "active is required")
        Boolean active
) {
}
