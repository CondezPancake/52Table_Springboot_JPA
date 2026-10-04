package springboot.domain.providermodelai.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;

public record ProviderModelAiRegisteredEvent(
        ProviderModelAiId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ProviderModelAiRegisteredEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
