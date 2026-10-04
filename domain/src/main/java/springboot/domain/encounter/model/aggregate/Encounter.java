package springboot.domain.encounter.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.encounter.event.EncounterRegisteredEvent;
import springboot.domain.encounter.event.EncounterUpdatedEvent;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public class Encounter extends AggregateRoot {
    private final EncounterId id;
    private ClinicalRecordId clinicalRecordId;
    private ProfessionalId professionalId;
    private EncounterTypeId encounterTypeId;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String reasonForVisit;
    private String currentCondition;
    private EncounterModalityId modalityId;
    private EncounterStatusId statusId;
    private ProfessionalId createdBy;
    private ProfessionalId updatedBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Encounter(
            EncounterId id,
            ClinicalRecordId clinicalRecordId,
            ProfessionalId professionalId,
            EncounterTypeId encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            EncounterModalityId modalityId,
            EncounterStatusId statusId,
            ProfessionalId createdBy,
            ProfessionalId updatedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.clinicalRecordId = Objects.requireNonNull(clinicalRecordId, "clinicalRecordId must not be null");
        this.professionalId = Objects.requireNonNull(professionalId, "professionalId must not be null");
        this.encounterTypeId = Objects.requireNonNull(encounterTypeId, "encounterTypeId must not be null");
        this.startedAt = Objects.requireNonNull(startedAt, "startedAt must not be null");
        this.endedAt = Objects.requireNonNull(endedAt, "endedAt must not be null");
        this.reasonForVisit = Objects.requireNonNull(reasonForVisit, "reasonForVisit must not be null");
        this.currentCondition = Objects.requireNonNull(currentCondition, "currentCondition must not be null");
        this.modalityId = Objects.requireNonNull(modalityId, "modalityId must not be null");
        this.statusId = Objects.requireNonNull(statusId, "statusId must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
        this.updatedBy = Objects.requireNonNull(updatedBy, "updatedBy must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static Encounter register(
            ClinicalRecordId clinicalRecordId,
            ProfessionalId professionalId,
            EncounterTypeId encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            EncounterModalityId modalityId,
            EncounterStatusId statusId,
            ProfessionalId createdBy,
            ProfessionalId updatedBy) {
        EncounterId id = EncounterId.generate();
        LocalDateTime now = LocalDateTime.now();
        Encounter aggregate = new Encounter(
                id,
                clinicalRecordId,
                professionalId,
                encounterTypeId,
                startedAt,
                endedAt,
                reasonForVisit,
                currentCondition,
                modalityId,
                statusId,
                createdBy,
                updatedBy,
                now,
                now);
        aggregate.recordEvent(new EncounterRegisteredEvent(id, now));
        return aggregate;
    }

    public static Encounter restore(
            EncounterId id,
            ClinicalRecordId clinicalRecordId,
            ProfessionalId professionalId,
            EncounterTypeId encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            EncounterModalityId modalityId,
            EncounterStatusId statusId,
            ProfessionalId createdBy,
            ProfessionalId updatedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new Encounter(
                id,
                clinicalRecordId,
                professionalId,
                encounterTypeId,
                startedAt,
                endedAt,
                reasonForVisit,
                currentCondition,
                modalityId,
                statusId,
                createdBy,
                updatedBy,
                createdAt,
                updatedAt);
    }

    public void update(
            ClinicalRecordId clinicalRecordId,
            ProfessionalId professionalId,
            EncounterTypeId encounterTypeId,
            LocalDateTime startedAt,
            LocalDateTime endedAt,
            String reasonForVisit,
            String currentCondition,
            EncounterModalityId modalityId,
            EncounterStatusId statusId,
            ProfessionalId createdBy,
            ProfessionalId updatedBy) {
        this.clinicalRecordId = Objects.requireNonNull(clinicalRecordId, "clinicalRecordId must not be null");
        this.professionalId = Objects.requireNonNull(professionalId, "professionalId must not be null");
        this.encounterTypeId = Objects.requireNonNull(encounterTypeId, "encounterTypeId must not be null");
        this.startedAt = Objects.requireNonNull(startedAt, "startedAt must not be null");
        this.endedAt = Objects.requireNonNull(endedAt, "endedAt must not be null");
        this.reasonForVisit = Objects.requireNonNull(reasonForVisit, "reasonForVisit must not be null");
        this.currentCondition = Objects.requireNonNull(currentCondition, "currentCondition must not be null");
        this.modalityId = Objects.requireNonNull(modalityId, "modalityId must not be null");
        this.statusId = Objects.requireNonNull(statusId, "statusId must not be null");
        this.createdBy = Objects.requireNonNull(createdBy, "createdBy must not be null");
        this.updatedBy = Objects.requireNonNull(updatedBy, "updatedBy must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new EncounterUpdatedEvent(
                        this.id,
                        this.clinicalRecordId,
                        this.professionalId,
                        this.encounterTypeId,
                        this.startedAt,
                        this.endedAt,
                        this.reasonForVisit,
                        this.currentCondition,
                        this.modalityId,
                        this.statusId,
                        this.createdBy,
                        this.updatedBy,
                        this.updatedAt));
    }

    public EncounterId id() {
        return id;
    }

    public ClinicalRecordId clinicalRecordId() {
        return clinicalRecordId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }

    public EncounterTypeId encounterTypeId() {
        return encounterTypeId;
    }

    public LocalDateTime startedAt() {
        return startedAt;
    }

    public LocalDateTime endedAt() {
        return endedAt;
    }

    public String reasonForVisit() {
        return reasonForVisit;
    }

    public String currentCondition() {
        return currentCondition;
    }

    public EncounterModalityId modalityId() {
        return modalityId;
    }

    public EncounterStatusId statusId() {
        return statusId;
    }

    public ProfessionalId createdBy() {
        return createdBy;
    }

    public ProfessionalId updatedBy() {
        return updatedBy;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
