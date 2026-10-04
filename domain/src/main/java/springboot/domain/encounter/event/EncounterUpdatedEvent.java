package springboot.domain.encounter.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record EncounterUpdatedEvent(
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
        LocalDateTime occurredOn
) implements DomainEvent {
    public EncounterUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(clinicalRecordId, "clinicalRecordId must not be null");
        Objects.requireNonNull(professionalId, "professionalId must not be null");
        Objects.requireNonNull(encounterTypeId, "encounterTypeId must not be null");
        Objects.requireNonNull(startedAt, "startedAt must not be null");
        Objects.requireNonNull(endedAt, "endedAt must not be null");
        Objects.requireNonNull(reasonForVisit, "reasonForVisit must not be null");
        Objects.requireNonNull(currentCondition, "currentCondition must not be null");
        Objects.requireNonNull(modalityId, "modalityId must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(createdBy, "createdBy must not be null");
        Objects.requireNonNull(updatedBy, "updatedBy must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
