package springboot.domain.patientallergy.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.patientallergy.model.valueobject.PatientAllergyId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record PatientAllergyUpdatedEvent(
        PatientAllergyId id,
        PatientId patientId,
        String substance,
        String reaction,
        String severity,
        boolean active,
        LocalDateTime recordedAt,
        ProfessionalId recordedBy,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PatientAllergyUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(patientId, "patientId must not be null");
        Objects.requireNonNull(substance, "substance must not be null");
        Objects.requireNonNull(severity, "severity must not be null");
        Objects.requireNonNull(recordedAt, "recordedAt must not be null");
        Objects.requireNonNull(recordedBy, "recordedBy must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
