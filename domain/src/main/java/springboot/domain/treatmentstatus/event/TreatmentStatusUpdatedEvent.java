package springboot.domain.treatmentstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;


public record TreatmentStatusUpdatedEvent(
        TreatmentStatusId id,
        String code,
        String name,
        boolean active,
        LocalDateTime occurredOn
) implements DomainEvent {
    public TreatmentStatusUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
