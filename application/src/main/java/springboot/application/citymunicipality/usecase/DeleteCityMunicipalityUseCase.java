package springboot.application.citymunicipality.usecase;

import java.time.LocalDateTime;

import springboot.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import springboot.domain.citymunicipality.event.CityMunicipalityDeletedEvent;
import springboot.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import springboot.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class DeleteCityMunicipalityUseCase {
    private final CityMunicipalityRepository repository;
    public DeleteCityMunicipalityUseCase(CityMunicipalityRepository repository) { this.repository = repository; }

    public CityMunicipalityDeletedEvent execute(CityMunicipalityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CityMunicipalityNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new CityMunicipalityDeletedEvent(id, LocalDateTime.now());
    }
}
