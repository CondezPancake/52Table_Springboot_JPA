package springboot.domain.diagnosticsystem.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public record DiagnosticSystemRegisteredEvent(
        DiagnosticSystemId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DiagnosticSystemRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
