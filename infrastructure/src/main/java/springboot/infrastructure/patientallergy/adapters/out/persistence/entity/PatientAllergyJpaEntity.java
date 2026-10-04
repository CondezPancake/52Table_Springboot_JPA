package springboot.infrastructure.patientallergy.adapters.out.persistence.entity;

import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "patient_allergies")
public class PatientAllergyJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "patient_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID patientId;

    @Column(name = "substance", nullable = false, length = 200)
    private String substance;

    @Column(name = "reaction", nullable = true, columnDefinition = "text")
    private String reaction;

    @Column(name = "severity", nullable = false, length = 20)
    private String severity;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "recorded_at", nullable = false)
    private LocalDateTime recordedAt;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "recorded_by", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID recordedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public PatientAllergyJpaEntity() { }
    public PatientAllergyJpaEntity(
            UUID id,
            UUID patientId,
            String substance,
            String reaction,
            String severity,
            boolean active,
            LocalDateTime recordedAt,
            UUID recordedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.patientId = patientId;
        this.substance = substance;
        this.reaction = reaction;
        this.severity = severity;
        this.active = active;
        this.recordedAt = recordedAt;
        this.recordedBy = recordedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public String getSubstance() {
        return substance;
    }

    public void setSubstance(String substance) {
        this.substance = substance;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }

    public UUID getRecordedBy() {
        return recordedBy;
    }

    public void setRecordedBy(UUID recordedBy) {
        this.recordedBy = recordedBy;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
