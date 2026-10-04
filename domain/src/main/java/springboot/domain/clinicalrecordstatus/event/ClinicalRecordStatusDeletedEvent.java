package springboot.domain.clinicalrecordstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public record ClinicalRecordStatusDeletedEvent(
        ClinicalRecordStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ClinicalRecordStatusDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
