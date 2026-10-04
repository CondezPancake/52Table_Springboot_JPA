package springboot.domain.citymunicipality.event;

import java.time.LocalDateTime;
import java.util.Objects;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.stateregion.model.valueobject.StateRegionId;

public record CityMunicipalityUpdatedEvent(
        CityMunicipalityId id,
        String nameCity,
        String codeCity,
        String description,
        boolean active,
        StateRegionId regionId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public CityMunicipalityUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameCity, "nameCity must not be null");
        Objects.requireNonNull(codeCity, "codeCity must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(regionId, "regionId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
