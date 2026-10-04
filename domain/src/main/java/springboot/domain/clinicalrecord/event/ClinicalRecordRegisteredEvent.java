package springboot.domain.clinicalrecord.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public record ClinicalRecordRegisteredEvent(
        ClinicalRecordId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ClinicalRecordRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
