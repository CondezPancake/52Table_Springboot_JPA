package springboot.domain.treatmentgoal.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

public record TreatmentGoalDeletedEvent(
        TreatmentGoalId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public TreatmentGoalDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
