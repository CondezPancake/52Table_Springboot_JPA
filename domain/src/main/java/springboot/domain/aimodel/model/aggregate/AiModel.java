package springboot.domain.aimodel.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.math.BigDecimal;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.aimodel.event.AiModelRegisteredEvent;
import springboot.domain.aimodel.event.AiModelUpdatedEvent;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;

public class AiModel extends AggregateRoot {
    private final AiModelId id;
    private ProviderModelAiId providerModelId;
    private String nameModel;
    private String modelKey;
    private BigDecimal inputTokenPrice;
    private BigDecimal outputTokenPrice;
    private int maxTokens;
    private int contextWindow;
    private boolean active;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private AiModel(
            AiModelId id,
            ProviderModelAiId providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            int maxTokens,
            int contextWindow,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.providerModelId = Objects.requireNonNull(providerModelId, "providerModelId must not be null");
        this.nameModel = Objects.requireNonNull(nameModel, "nameModel must not be null");
        this.modelKey = Objects.requireNonNull(modelKey, "modelKey must not be null");
        this.inputTokenPrice = Objects.requireNonNull(inputTokenPrice, "inputTokenPrice must not be null");
        this.outputTokenPrice = Objects.requireNonNull(outputTokenPrice, "outputTokenPrice must not be null");
        this.maxTokens = maxTokens;
        this.contextWindow = contextWindow;
        this.active = active;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static AiModel register(
            ProviderModelAiId providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            int maxTokens,
            int contextWindow,
            boolean active) {
        AiModelId id = AiModelId.generate();
        LocalDateTime now = LocalDateTime.now();
        AiModel aggregate = new AiModel(
                id,
                providerModelId,
                nameModel,
                modelKey,
                inputTokenPrice,
                outputTokenPrice,
                maxTokens,
                contextWindow,
                active,
                now,
                now);
        aggregate.recordEvent(new AiModelRegisteredEvent(id, now));
        return aggregate;
    }

    public static AiModel restore(
            AiModelId id,
            ProviderModelAiId providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            int maxTokens,
            int contextWindow,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new AiModel(
                id,
                providerModelId,
                nameModel,
                modelKey,
                inputTokenPrice,
                outputTokenPrice,
                maxTokens,
                contextWindow,
                active,
                createdAt,
                updatedAt);
    }

    public void update(
            ProviderModelAiId providerModelId,
            String nameModel,
            String modelKey,
            BigDecimal inputTokenPrice,
            BigDecimal outputTokenPrice,
            int maxTokens,
            int contextWindow,
            boolean active) {
        this.providerModelId = Objects.requireNonNull(providerModelId, "providerModelId must not be null");
        this.nameModel = Objects.requireNonNull(nameModel, "nameModel must not be null");
        this.modelKey = Objects.requireNonNull(modelKey, "modelKey must not be null");
        this.inputTokenPrice = Objects.requireNonNull(inputTokenPrice, "inputTokenPrice must not be null");
        this.outputTokenPrice = Objects.requireNonNull(outputTokenPrice, "outputTokenPrice must not be null");
        this.maxTokens = maxTokens;
        this.contextWindow = contextWindow;
        this.active = active;
        this.updatedAt = LocalDateTime.now();
        recordEvent(new AiModelUpdatedEvent(
                        this.id,
                        this.providerModelId,
                        this.nameModel,
                        this.modelKey,
                        this.inputTokenPrice,
                        this.outputTokenPrice,
                        this.maxTokens,
                        this.contextWindow,
                        this.active,
                        this.updatedAt));
    }

    public AiModelId id() {
        return id;
    }

    public ProviderModelAiId providerModelId() {
        return providerModelId;
    }

    public String nameModel() {
        return nameModel;
    }

    public String modelKey() {
        return modelKey;
    }

    public BigDecimal inputTokenPrice() {
        return inputTokenPrice;
    }

    public BigDecimal outputTokenPrice() {
        return outputTokenPrice;
    }

    public int maxTokens() {
        return maxTokens;
    }

    public int contextWindow() {
        return contextWindow;
    }

    public boolean active() {
        return active;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
