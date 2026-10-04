package springboot.domain.clinicalnote.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.clinicalnote.model.valueobject.ClinicalNoteId;

public record ClinicalNoteDeletedEvent(
        ClinicalNoteId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ClinicalNoteDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
