package springboot.application.chatairunmetric.command;

import java.util.Objects;
import java.math.BigDecimal;

import springboot.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

public record UpdateChatAiRunMetricCommand(
        ChatAiRunMetricId id,
        ChatAiRunId aiRunId,
        int promptTokens,
        int completionTokens,
        int totalTokens,
        BigDecimal cost
) {
    public UpdateChatAiRunMetricCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(aiRunId, "aiRunId must not be null");
        Objects.requireNonNull(cost, "cost must not be null");
    }
}
