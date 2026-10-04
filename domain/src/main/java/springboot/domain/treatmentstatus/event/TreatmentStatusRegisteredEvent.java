package springboot.domain.treatmentstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public record TreatmentStatusRegisteredEvent(
        TreatmentStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public TreatmentStatusRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
