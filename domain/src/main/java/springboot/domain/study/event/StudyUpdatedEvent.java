package springboot.domain.study.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.study.model.valueobject.StudyId;


public record StudyUpdatedEvent(
        StudyId id,
        String name,
        LocalDateTime occurredOn
) implements DomainEvent {
    public StudyUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
