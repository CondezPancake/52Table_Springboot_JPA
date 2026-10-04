package springboot.domain.chatmessage.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatmessage.model.valueobject.ChatMessageId;

public record ChatMessageDeletedEvent(
        ChatMessageId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatMessageDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
