package springboot.domain.chatairun.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

public record ChatAiRunDeletedEvent(
        ChatAiRunId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatAiRunDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
