package springboot.domain.mentalstatusexam.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public record MentalStatusExamDeletedEvent(
        MentalStatusExamId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public MentalStatusExamDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
