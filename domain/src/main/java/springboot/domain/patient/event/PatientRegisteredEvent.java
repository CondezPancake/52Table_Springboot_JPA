package springboot.domain.patient.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.patient.model.valueobject.PatientId;

public record PatientRegisteredEvent(
        PatientId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PatientRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
