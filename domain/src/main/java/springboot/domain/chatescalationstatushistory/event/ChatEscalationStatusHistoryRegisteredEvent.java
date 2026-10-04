package springboot.domain.chatescalationstatushistory.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public record ChatEscalationStatusHistoryRegisteredEvent(
        ChatEscalationStatusHistoryId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatEscalationStatusHistoryRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
