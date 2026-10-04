package springboot.domain.chatescalation.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record ChatEscalationUpdatedEvent(
        ChatEscalationId id,
        ChatConversationId conversationId,
        EscalationStatusId statusId,
        boolean fromAi,
        String reason,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatEscalationUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationId, "conversationId must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(reason, "reason must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
