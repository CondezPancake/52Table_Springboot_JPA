package springboot.infrastructure.chatescalationassignment.adapters.out.persistence.entity;


import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "chat_escalation_assignments")
public class ChatEscalationAssignmentJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "escalation_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID escalationId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "professional_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID professionalId;

    @Column(name = "assigned_at", nullable = false)
    private LocalDateTime assignedAt;



    public ChatEscalationAssignmentJpaEntity() { }
    public ChatEscalationAssignmentJpaEntity(
            UUID id,
            UUID escalationId,
            UUID professionalId,
            LocalDateTime assignedAt) {
        this.id = id;
        this.escalationId = escalationId;
        this.professionalId = professionalId;
        this.assignedAt = assignedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getEscalationId() {
        return escalationId;
    }

    public void setEscalationId(UUID escalationId) {
        this.escalationId = escalationId;
    }

    public UUID getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(UUID professionalId) {
        this.professionalId = professionalId;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }


}
