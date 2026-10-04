package springboot.domain.diagnosticsystem.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;


public record DiagnosticSystemUpdatedEvent(
        DiagnosticSystemId id,
        String code,
        String name,
        boolean active,
        String version,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DiagnosticSystemUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(version, "version must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
