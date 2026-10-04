package springboot.domain.chatairunerror.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

public record ChatAiRunErrorUpdatedEvent(
        ChatAiRunErrorId id,
        ChatAiRunId aiRunId,
        String errorMessage,
        String errorCode,
        String providerErrorId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ChatAiRunErrorUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(aiRunId, "aiRunId must not be null");
        Objects.requireNonNull(errorMessage, "errorMessage must not be null");
        Objects.requireNonNull(errorCode, "errorCode must not be null");
        Objects.requireNonNull(providerErrorId, "providerErrorId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
