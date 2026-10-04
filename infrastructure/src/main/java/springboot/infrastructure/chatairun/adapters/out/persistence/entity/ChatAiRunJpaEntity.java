package springboot.infrastructure.chatairun.adapters.out.persistence.entity;

import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "chat_ai_runs")
public class ChatAiRunJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "conversation_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID conversationId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "message_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID messageId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "model_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID modelId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "ai_run_status_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID aiRunStatusId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ChatAiRunJpaEntity() { }
    public ChatAiRunJpaEntity(
            UUID id,
            UUID conversationId,
            UUID messageId,
            UUID modelId,
            UUID aiRunStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.messageId = messageId;
        this.modelId = modelId;
        this.aiRunStatusId = aiRunStatusId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getConversationId() {
        return conversationId;
    }

    public void setConversationId(UUID conversationId) {
        this.conversationId = conversationId;
    }

    public UUID getMessageId() {
        return messageId;
    }

    public void setMessageId(UUID messageId) {
        this.messageId = messageId;
    }

    public UUID getModelId() {
        return modelId;
    }

    public void setModelId(UUID modelId) {
        this.modelId = modelId;
    }

    public UUID getAiRunStatusId() {
        return aiRunStatusId;
    }

    public void setAiRunStatusId(UUID aiRunStatusId) {
        this.aiRunStatusId = aiRunStatusId;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
