package springboot.domain.chatescalationstatushistory.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record ChatEscalationStatusHistoryUpdatedEvent(
        ChatEscalationStatusHistoryId id,
        ChatEscalationId escalationId,
        EscalationStatusId escalationStatusId,
        LocalDateTime changedAt,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatEscalationStatusHistoryUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(escalationId, "escalationId must not be null");
        Objects.requireNonNull(escalationStatusId, "escalationStatusId must not be null");
        Objects.requireNonNull(changedAt, "changedAt must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
