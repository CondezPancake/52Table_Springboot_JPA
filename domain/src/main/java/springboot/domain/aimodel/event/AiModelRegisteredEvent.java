package springboot.domain.aimodel.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.aimodel.model.valueobject.AiModelId;

public record AiModelRegisteredEvent(
        AiModelId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public AiModelRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
