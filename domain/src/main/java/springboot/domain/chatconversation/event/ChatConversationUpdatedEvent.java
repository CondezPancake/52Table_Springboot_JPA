package springboot.domain.chatconversation.event;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.priority.model.valueobject.PriorityId;

public record ChatConversationUpdatedEvent(
        ChatConversationId id,
        ConversationStatusId conversationStatusId,
        PriorityId priorityId,
        LocalDateTime lastMessageAt,
        Boolean closed,
        LocalDateTime closedAt,
        UUID closedBy,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatConversationUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(conversationStatusId, "conversationStatusId must not be null");
        Objects.requireNonNull(priorityId, "priorityId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
