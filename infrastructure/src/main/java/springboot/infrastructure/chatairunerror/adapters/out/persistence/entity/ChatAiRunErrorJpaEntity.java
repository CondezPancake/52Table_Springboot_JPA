package springboot.infrastructure.chatairunerror.adapters.out.persistence.entity;

import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "chat_ai_run_errors")
public class ChatAiRunErrorJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "ai_run_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID aiRunId;

    @Column(name = "error_message", nullable = false, columnDefinition = "text")
    private String errorMessage;

    @Column(name = "error_code", nullable = false, length = 80)
    private String errorCode;

    @Column(name = "provider_error_id", nullable = false, length = 120)
    private String providerErrorId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public ChatAiRunErrorJpaEntity() { }
    public ChatAiRunErrorJpaEntity(
            UUID id,
            UUID aiRunId,
            String errorMessage,
            String errorCode,
            String providerErrorId,
            LocalDateTime createdAt) {
        this.id = id;
        this.aiRunId = aiRunId;
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.providerErrorId = providerErrorId;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getAiRunId() {
        return aiRunId;
    }

    public void setAiRunId(UUID aiRunId) {
        this.aiRunId = aiRunId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getProviderErrorId() {
        return providerErrorId;
    }

    public void setProviderErrorId(String providerErrorId) {
        this.providerErrorId = providerErrorId;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
