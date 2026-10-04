package springboot.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity;

import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "chat_escalation_status_history")
public class ChatEscalationStatusHistoryJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "escalation_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID escalationId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "escalation_status_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID escalationStatusId;

    @Column(name = "changed_at", nullable = false)
    private LocalDateTime changedAt;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public ChatEscalationStatusHistoryJpaEntity() { }
    public ChatEscalationStatusHistoryJpaEntity(
            UUID id,
            UUID escalationId,
            UUID escalationStatusId,
            LocalDateTime changedAt,
            LocalDateTime createdAt) {
        this.id = id;
        this.escalationId = escalationId;
        this.escalationStatusId = escalationStatusId;
        this.changedAt = changedAt;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEscalationId() {
        return escalationId;
    }

    public void setEscalationId(UUID escalationId) {
        this.escalationId = escalationId;
    }

    public UUID getEscalationStatusId() {
        return escalationStatusId;
    }

    public void setEscalationStatusId(UUID escalationStatusId) {
        this.escalationStatusId = escalationStatusId;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
