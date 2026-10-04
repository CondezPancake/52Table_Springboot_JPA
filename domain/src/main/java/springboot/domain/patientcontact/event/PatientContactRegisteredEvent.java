package springboot.domain.patientcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.patientcontact.model.valueobject.PatientContactId;

public record PatientContactRegisteredEvent(
        PatientContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PatientContactRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
