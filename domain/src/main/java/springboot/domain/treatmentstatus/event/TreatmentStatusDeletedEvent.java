package springboot.domain.treatmentstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public record TreatmentStatusDeletedEvent(
        TreatmentStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public TreatmentStatusDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
