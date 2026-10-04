package springboot.domain.common.exception;

import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;

public class CityMunicipalityNotFoundException extends RuntimeException {
    public CityMunicipalityNotFoundException(CityMunicipalityId id) {
        super("CityMunicipality not found with id: " + id.value());
    }
}
