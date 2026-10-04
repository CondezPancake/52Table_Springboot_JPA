package springboot.application.citymunicipality.usecase;

import springboot.application.citymunicipality.command.UpdateCityMunicipalityCommand;
import springboot.application.citymunicipality.dto.CityMunicipalityResponse;
import springboot.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import springboot.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class UpdateCityMunicipalityUseCase {
    private final CityMunicipalityRepository repository;
    public UpdateCityMunicipalityUseCase(CityMunicipalityRepository repository) { this.repository = repository; }

    public CityMunicipalityResponse execute(UpdateCityMunicipalityCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new CityMunicipalityNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.nameCity(),
                command.codeCity(),
                command.description(),
                command.active(),
                command.regionId());
        var saved = repository.save(aggregate);
        return new CityMunicipalityResponse(
                saved.id().value(),
                saved.nameCity(),
                saved.codeCity(),
                saved.description(),
                saved.active(),
                saved.regionId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
