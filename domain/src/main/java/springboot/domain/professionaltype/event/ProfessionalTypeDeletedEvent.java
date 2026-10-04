package springboot.domain.professionaltype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public record ProfessionalTypeDeletedEvent(
        ProfessionalTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ProfessionalTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
