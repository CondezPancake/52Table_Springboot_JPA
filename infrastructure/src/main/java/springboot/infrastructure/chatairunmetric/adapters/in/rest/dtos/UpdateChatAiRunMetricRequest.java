package springboot.infrastructure.chatairunmetric.adapters.in.rest.dtos;

import java.util.UUID;

import java.math.BigDecimal;
import jakarta.validation.constraints.Digits;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateChatAiRunMetricRequest(
        @NotNull(message = "aiRunId is required")
        UUID aiRunId,

        @NotNull(message = "promptTokens is required")
        Integer promptTokens,

        @NotNull(message = "completionTokens is required")
        Integer completionTokens,

        @NotNull(message = "totalTokens is required")
        Integer totalTokens,

        @NotNull(message = "cost is required")
        @Digits(integer = 4, fraction = 6, message = "cost must fit DECIMAL(10,6)")
        BigDecimal cost
) {
}
