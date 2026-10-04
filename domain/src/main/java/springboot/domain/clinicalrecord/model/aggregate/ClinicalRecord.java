package springboot.domain.clinicalrecord.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.clinicalrecord.event.ClinicalRecordRegisteredEvent;
import springboot.domain.clinicalrecord.event.ClinicalRecordUpdatedEvent;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public class ClinicalRecord extends AggregateRoot {
    private final ClinicalRecordId id;
    private PatientId patientId;
    private LocalDateTime creationDate;
    private String recordNumber;
    private LocalDateTime openedAt;
    private LocalDateTime closedAt;
    private ClinicalRecordStatusId statusId;
    private ProfessionalId createdBy;
    private final LocalDateTime createdAt;

    private ClinicalRecord(
            ClinicalRecordId id,
            PatientId patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            ClinicalRecordStatusId statusId,
            ProfessionalId createdBy,
            LocalDateTime createdAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.patientId = Objects.requireNonNull(patientId, "patientId must not be null");
        this.creationDate = Objects.requireNonNull(creationDate, "creationDate must not be null");
        this.recordNumber = Objects.requireNonNull(recordNumber, "recordNumber must not be null");
        this.openedAt = Objects.requireNonNull(openedAt, "openedAt must not be null");
        this.closedAt = Objects.requireNonNull(closedAt, "closedAt must not be null");
        this.statusId = Objects.requireNonNull(statusId, "statusId must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
    }

    public static ClinicalRecord register(
            PatientId patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            ClinicalRecordStatusId statusId,
            ProfessionalId createdBy) {
        ClinicalRecordId id = ClinicalRecordId.generate();
        LocalDateTime now = LocalDateTime.now();
        ClinicalRecord aggregate = new ClinicalRecord(
                id,
                patientId,
                creationDate,
                recordNumber,
                openedAt,
                closedAt,
                statusId,
                createdBy,
                now);
        aggregate.recordEvent(new ClinicalRecordRegisteredEvent(id, now));
        return aggregate;
    }

    public static ClinicalRecord restore(
            ClinicalRecordId id,
            PatientId patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            ClinicalRecordStatusId statusId,
            ProfessionalId createdBy,
            LocalDateTime createdAt) {
        return new ClinicalRecord(
                id,
                patientId,
                creationDate,
                recordNumber,
                openedAt,
                closedAt,
                statusId,
                createdBy,
                createdAt);
    }

    public void update(
            PatientId patientId,
            LocalDateTime creationDate,
            String recordNumber,
            LocalDateTime openedAt,
            LocalDateTime closedAt,
            ClinicalRecordStatusId statusId,
            ProfessionalId createdBy) {
        this.patientId = Objects.requireNonNull(patientId, "patientId must not be null");
        this.creationDate = Objects.requireNonNull(creationDate, "creationDate must not be null");
        this.recordNumber = Objects.requireNonNull(recordNumber, "recordNumber must not be null");
        this.openedAt = Objects.requireNonNull(openedAt, "openedAt must not be null");
        this.closedAt = Objects.requireNonNull(closedAt, "closedAt must not be null");
        this.statusId = Objects.requireNonNull(statusId, "statusId must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new ClinicalRecordUpdatedEvent(
                        this.id,
                        this.patientId,
                        this.creationDate,
                        this.recordNumber,
                        this.openedAt,
                        this.closedAt,
                        this.statusId,
                        this.createdBy,
                        occurredOn));
    }

    public ClinicalRecordId id() {
        return id;
    }

    public PatientId patientId() {
        return patientId;
    }

    public LocalDateTime creationDate() {
        return creationDate;
    }

    public String recordNumber() {
        return recordNumber;
    }

    public LocalDateTime openedAt() {
        return openedAt;
    }

    public LocalDateTime closedAt() {
        return closedAt;
    }

    public ClinicalRecordStatusId statusId() {
        return statusId;
    }

    public ProfessionalId createdBy() {
        return createdBy;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }
}
