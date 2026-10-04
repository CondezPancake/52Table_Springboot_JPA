package springboot.application.citymunicipality.command;

import java.util.Objects;

import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.stateregion.model.valueobject.StateRegionId;

public record UpdateCityMunicipalityCommand(
        CityMunicipalityId id,
        String nameCity,
        String codeCity,
        String description,
        boolean active,
        StateRegionId regionId
) {
    public UpdateCityMunicipalityCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameCity, "nameCity must not be null");
        Objects.requireNonNull(codeCity, "codeCity must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(regionId, "regionId must not be null");
    }
}
