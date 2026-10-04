package springboot.domain.providermodelai.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;

public record ProviderModelAiDeletedEvent(
        ProviderModelAiId id,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ProviderModelAiDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
