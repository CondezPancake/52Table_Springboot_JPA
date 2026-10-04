package springboot.infrastructure.chatescalation.adapters.out.persistence.entity;

import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "chat_escalations")
public class ChatEscalationJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "conversation_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID conversationId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "status_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID statusId;

    @Column(name = "from_ai", nullable = false)
    private boolean fromAi;

    @Column(name = "reason", nullable = false, columnDefinition = "text")
    private String reason;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public ChatEscalationJpaEntity() { }
    public ChatEscalationJpaEntity(
            UUID id,
            UUID conversationId,
            UUID statusId,
            boolean fromAi,
            String reason,
            LocalDateTime createdAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.statusId = statusId;
        this.fromAi = fromAi;
        this.reason = reason;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getConversationId() {
        return conversationId;
    }

    public void setConversationId(UUID conversationId) {
        this.conversationId = conversationId;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public void setStatusId(UUID statusId) {
        this.statusId = statusId;
    }

    public boolean isFromAi() {
        return fromAi;
    }

    public void setFromAi(boolean fromAi) {
        this.fromAi = fromAi;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
