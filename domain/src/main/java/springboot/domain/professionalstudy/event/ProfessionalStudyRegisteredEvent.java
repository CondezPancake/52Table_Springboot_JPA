package springboot.domain.professionalstudy.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public record ProfessionalStudyRegisteredEvent(
        ProfessionalStudyId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ProfessionalStudyRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
