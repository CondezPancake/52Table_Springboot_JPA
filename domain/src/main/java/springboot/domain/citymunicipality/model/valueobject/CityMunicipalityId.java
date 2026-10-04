package springboot.domain.citymunicipality.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record CityMunicipalityId(UUID value) {
    public CityMunicipalityId {
        Objects.requireNonNull(value, "CityMunicipalityId value must not be null");
    }

    public static CityMunicipalityId generate() {
        return new CityMunicipalityId(UUID.randomUUID());
    }
}
