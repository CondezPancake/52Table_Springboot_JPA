package springboot.domain.encounter.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.encounter.model.valueobject.EncounterId;

public record EncounterRegisteredEvent(
        EncounterId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public EncounterRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
