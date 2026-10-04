package springboot.domain.chatairunmetric.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.math.BigDecimal;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.chatairunmetric.event.ChatAiRunMetricRegisteredEvent;
import springboot.domain.chatairunmetric.event.ChatAiRunMetricUpdatedEvent;
import springboot.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

public class ChatAiRunMetric extends AggregateRoot {
    private final ChatAiRunMetricId id;
    private ChatAiRunId aiRunId;
    private int promptTokens;
    private int completionTokens;
    private int totalTokens;
    private BigDecimal cost;
    private final LocalDateTime createdAt;

    private ChatAiRunMetric(
            ChatAiRunMetricId id,
            ChatAiRunId aiRunId,
            int promptTokens,
            int completionTokens,
            int totalTokens,
            BigDecimal cost,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.aiRunId = Objects.requireNonNull(aiRunId, "aiRunId must not be null");
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = totalTokens;
        this.cost = Objects.requireNonNull(cost, "cost must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static ChatAiRunMetric register(
            ChatAiRunId aiRunId,
            int promptTokens,
            int completionTokens,
            int totalTokens,
            BigDecimal cost) {
        ChatAiRunMetricId id = ChatAiRunMetricId.generate();
        LocalDateTime now = LocalDateTime.now();
        ChatAiRunMetric aggregate = new ChatAiRunMetric(
                id,
                aiRunId,
                promptTokens,
                completionTokens,
                totalTokens,
                cost,
                now);
        aggregate.recordEvent(new ChatAiRunMetricRegisteredEvent(id, now));
        return aggregate;
    }

    public static ChatAiRunMetric restore(
            ChatAiRunMetricId id,
            ChatAiRunId aiRunId,
            int promptTokens,
            int completionTokens,
            int totalTokens,
            BigDecimal cost,
            LocalDateTime createdAt) {
        return new ChatAiRunMetric(
                id,
                aiRunId,
                promptTokens,
                completionTokens,
                totalTokens,
                cost,
                createdAt);
    }

    public void update(
            ChatAiRunId aiRunId,
            int promptTokens,
            int completionTokens,
            int totalTokens,
            BigDecimal cost) {
        this.aiRunId = Objects.requireNonNull(aiRunId, "aiRunId must not be null");
        this.promptTokens = promptTokens;
        this.completionTokens = completionTokens;
        this.totalTokens = totalTokens;
        this.cost = Objects.requireNonNull(cost, "cost must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new ChatAiRunMetricUpdatedEvent(
                        this.id,
                        this.aiRunId,
                        this.promptTokens,
                        this.completionTokens,
                        this.totalTokens,
                        this.cost,
                        occurredOn));
    }

    public ChatAiRunMetricId id() {
        return id;
    }

    public ChatAiRunId aiRunId() {
        return aiRunId;
    }

    public int promptTokens() {
        return promptTokens;
    }

    public int completionTokens() {
        return completionTokens;
    }

    public int totalTokens() {
        return totalTokens;
    }

    public BigDecimal cost() {
        return cost;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
