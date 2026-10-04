package springboot.domain.encountermodality.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;

public record EncounterModalityDeletedEvent(
        EncounterModalityId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public EncounterModalityDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
