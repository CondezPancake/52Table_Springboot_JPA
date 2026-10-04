package springboot.domain.medicationroute.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.medicationroute.model.valueobject.MedicationRouteId;

public record MedicationRouteDeletedEvent(
        MedicationRouteId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public MedicationRouteDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
