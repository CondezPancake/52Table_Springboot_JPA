package springboot.domain.providermodelai.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;


public record ProviderModelAiUpdatedEvent(
        ProviderModelAiId id,
        String nameProviderAi,
        String razonSocial,
        String sitioWeb,
        boolean active,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ProviderModelAiUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameProviderAi, "nameProviderAi must not be null");
        Objects.requireNonNull(razonSocial, "razonSocial must not be null");
        Objects.requireNonNull(sitioWeb, "sitioWeb must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
