package springboot.infrastructure.encounter.adapters.out.persistence.entity;

import java.time.LocalDateTime;

import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "encounters")
public class EncounterJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "clinical_record_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID clinicalRecordId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "professional_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID professionalId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "encounter_type_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID encounterTypeId;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "ended_at", nullable = false)
    private LocalDateTime endedAt;

    @Column(name = "reason_for_visit", nullable = false, columnDefinition = "text")
    private String reasonForVisit;

    @Column(name = "current_condition", nullable = false, columnDefinition = "text")
    private String currentCondition;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "modality_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID modalityId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "status_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID statusId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "created_by", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID createdBy;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "updated_by", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID updatedBy;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public EncounterJpaEntity() { }
    public EncounterJpaEntity(
            UUID id,
            UUID clinicalRecordId,
            UUID professionalId,
            UUID encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            UUID modalityId,
            UUID statusId,
            UUID createdBy,
            UUID updatedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = id;
        this.clinicalRecordId = clinicalRecordId;
        this.professionalId = professionalId;
        this.encounterTypeId = encounterTypeId;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.reasonForVisit = reasonForVisit;
        this.currentCondition = currentCondition;
        this.modalityId = modalityId;
        this.statusId = statusId;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getClinicalRecordId() {
        return clinicalRecordId;
    }

    public void setClinicalRecordId(UUID clinicalRecordId) {
        this.clinicalRecordId = clinicalRecordId;
    }

    public UUID getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(UUID professionalId) {
        this.professionalId = professionalId;
    }

    public UUID getEncounterTypeId() {
        return encounterTypeId;
    }

    public void setEncounterTypeId(UUID encounterTypeId) {
        this.encounterTypeId = encounterTypeId;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public void setReasonForVisit(String reasonForVisit) {
        this.reasonForVisit = reasonForVisit;
    }

    public String getCurrentCondition() {
        return currentCondition;
    }

    public void setCurrentCondition(String currentCondition) {
        this.currentCondition = currentCondition;
    }

    public UUID getModalityId() {
        return modalityId;
    }

    public void setModalityId(UUID modalityId) {
        this.modalityId = modalityId;
    }

    public UUID getStatusId() {
        return statusId;
    }

    public void setStatusId(UUID statusId) {
        this.statusId = statusId;
    }

    public UUID getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
    }

    public UUID getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(UUID updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}
