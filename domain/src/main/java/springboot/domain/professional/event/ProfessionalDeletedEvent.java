package springboot.domain.professional.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record ProfessionalDeletedEvent(
        ProfessionalId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ProfessionalDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
