package springboot.infrastructure.chatparticipant.adapters.out.persistence.entity;

import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "chat_participants")
public class ChatParticipantJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "conversation_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID conversationId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "participant_type_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID participantTypeId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "patient_id", nullable = true, length = 36, columnDefinition = "char(36)")
    private UUID patientId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "professional_id", nullable = true, length = 36, columnDefinition = "char(36)")
    private UUID professionalId;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public ChatParticipantJpaEntity() { }
    public ChatParticipantJpaEntity(
            UUID id,
            UUID conversationId,
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.conversationId = conversationId;
        this.participantTypeId = participantTypeId;
        this.patientId = patientId;
        this.professionalId = professionalId;
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

    public UUID getParticipantTypeId() {
        return participantTypeId;
    }

    public void setParticipantTypeId(UUID participantTypeId) {
        this.participantTypeId = participantTypeId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public UUID getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(UUID professionalId) {
        this.professionalId = professionalId;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
