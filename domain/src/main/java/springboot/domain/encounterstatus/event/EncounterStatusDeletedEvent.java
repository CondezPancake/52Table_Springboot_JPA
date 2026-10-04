package springboot.domain.encounterstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;

public record EncounterStatusDeletedEvent(
        EncounterStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public EncounterStatusDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
