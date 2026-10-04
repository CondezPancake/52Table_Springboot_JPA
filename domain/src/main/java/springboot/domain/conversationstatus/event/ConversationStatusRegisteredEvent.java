package springboot.domain.conversationstatus.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;

public record ConversationStatusRegisteredEvent(
        ConversationStatusId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ConversationStatusRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
