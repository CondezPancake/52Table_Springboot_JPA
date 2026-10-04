package springboot.application.citymunicipality.usecase;

import springboot.application.citymunicipality.dto.CityMunicipalityResponse;
import springboot.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class GetCityMunicipalityByIdUseCase {
    private final CityMunicipalityRepository repository;
    public GetCityMunicipalityByIdUseCase(CityMunicipalityRepository repository) { this.repository = repository; }

    public CityMunicipalityResponse execute(CityMunicipalityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CityMunicipalityNotFoundApplicationException(id.value().toString()));
        return new CityMunicipalityResponse(
                aggregate.id().value(),
                aggregate.nameCity(),
                aggregate.codeCity(),
                aggregate.description(),
                aggregate.active(),
                aggregate.regionId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
