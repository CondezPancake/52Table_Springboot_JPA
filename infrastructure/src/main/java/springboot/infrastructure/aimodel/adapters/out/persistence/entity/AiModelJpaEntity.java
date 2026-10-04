package springboot.infrastructure.aimodel.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "ai_models")
public class AiModelJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "provider_model_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID providerModelId;

    @Column(name = "name_model", nullable = false, length = 100)
    private String nameModel;

    @Column(name = "model_key", nullable = false, length = 120)
    private String modelKey;

    @Column(name = "input_token_price", nullable = false, precision = 12, scale = 8)
    private BigDecimal inputTokenPrice;

    @Column(name = "output_token_price", nullable = false, precision = 12, scale = 8)
    private BigDecimal outputTokenPrice;

    @Column(name = "max_tokens", nullable = false)
    private int maxTokens;

    @Column(name = "context_window", nullable = false)
    private int contextWindow;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public AiModelJpaEntity() { }
    public AiModelJpaEntity(
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
            LocalDateTime updatedAt) {
        this.id = id;
        this.providerModelId = providerModelId;
        this.nameModel = nameModel;
        this.modelKey = modelKey;
        this.inputTokenPrice = inputTokenPrice;
        this.outputTokenPrice = outputTokenPrice;
        this.maxTokens = maxTokens;
        this.contextWindow = contextWindow;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getProviderModelId() {
        return providerModelId;
    }

    public void setProviderModelId(UUID providerModelId) {
        this.providerModelId = providerModelId;
    }

    public String getNameModel() {
        return nameModel;
    }

    public void setNameModel(String nameModel) {
        this.nameModel = nameModel;
    }

    public String getModelKey() {
        return modelKey;
    }

    public void setModelKey(String modelKey) {
        this.modelKey = modelKey;
    }

    public BigDecimal getInputTokenPrice() {
        return inputTokenPrice;
    }

    public void setInputTokenPrice(BigDecimal inputTokenPrice) {
        this.inputTokenPrice = inputTokenPrice;
    }

    public BigDecimal getOutputTokenPrice() {
        return outputTokenPrice;
    }

    public void setOutputTokenPrice(BigDecimal outputTokenPrice) {
        this.outputTokenPrice = outputTokenPrice;
    }

    public int getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(int maxTokens) {
        this.maxTokens = maxTokens;
    }

    public int getContextWindow() {
        return contextWindow;
    }

    public void setContextWindow(int contextWindow) {
        this.contextWindow = contextWindow;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
