package springboot.application.clinicalrecord.command;

import java.util.Objects;
import java.time.LocalDateTime;

import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record UpdateClinicalRecordCommand(
        ClinicalRecordId id,
        PatientId patientId,
        LocalDateTime creationDate,
        String recordNumber,
        LocalDateTime openedAt,
        LocalDateTime closedAt,
        ClinicalRecordStatusId statusId,
        ProfessionalId createdBy
) {
    public UpdateClinicalRecordCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(patientId, "patientId must not be null");
        Objects.requireNonNull(creationDate, "creationDate must not be null");
        Objects.requireNonNull(recordNumber, "recordNumber must not be null");
        Objects.requireNonNull(openedAt, "openedAt must not be null");
        Objects.requireNonNull(closedAt, "closedAt must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(createdBy, "createdBy must not be null");
    }
}
