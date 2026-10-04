package springboot.domain.patientallergy.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.patientallergy.model.valueobject.PatientAllergyId;

public record PatientAllergyDeletedEvent(
        PatientAllergyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PatientAllergyDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
