package springboot.domain.chatescalation.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;

public record ChatEscalationRegisteredEvent(
        ChatEscalationId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatEscalationRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
