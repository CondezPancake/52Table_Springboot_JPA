package springboot.domain.chatairunmetric.event;

import java.time.LocalDateTime;
import java.util.Objects;
import java.math.BigDecimal;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

public record ChatAiRunMetricUpdatedEvent(
        ChatAiRunMetricId id,
        ChatAiRunId aiRunId,
        int promptTokens,
        int completionTokens,
        int totalTokens,
        BigDecimal cost,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatAiRunMetricUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(aiRunId, "aiRunId must not be null");
        Objects.requireNonNull(cost, "cost must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
