package springboot.domain.stateregion.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.stateregion.model.valueobject.StateRegionId;
import springboot.domain.country.model.valueobject.CountryId;

public record StateRegionUpdatedEvent(
        StateRegionId id,
        String nameRegion,
        String codeRegion,
        String description,
        boolean active,
        CountryId countryId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public StateRegionUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameRegion, "nameRegion must not be null");
        Objects.requireNonNull(codeRegion, "codeRegion must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(countryId, "countryId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
