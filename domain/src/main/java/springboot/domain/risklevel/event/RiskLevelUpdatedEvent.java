package springboot.domain.risklevel.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.risklevel.model.valueobject.RiskLevelId;


public record RiskLevelUpdatedEvent(
        RiskLevelId id,
        String code,
        String name,
        boolean active,
        int severity,
        LocalDateTime occurredOn
) implements DomainEvent {
    public RiskLevelUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
