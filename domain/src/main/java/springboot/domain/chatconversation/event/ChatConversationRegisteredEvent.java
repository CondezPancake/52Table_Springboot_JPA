package springboot.domain.chatconversation.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatconversation.model.valueobject.ChatConversationId;

public record ChatConversationRegisteredEvent(
        ChatConversationId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatConversationRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
