package springboot.domain.documenttype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.documenttype.model.valueobject.DocumentTypeId;

public record DocumentTypeDeletedEvent(
        DocumentTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public DocumentTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
