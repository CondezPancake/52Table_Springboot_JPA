package springboot.application.citymunicipality.usecase;

import springboot.application.citymunicipality.command.RegisterCityMunicipalityCommand;
import springboot.application.citymunicipality.dto.CityMunicipalityResponse;
import springboot.domain.citymunicipality.model.aggregate.CityMunicipality;
import springboot.domain.citymunicipality.port.repository.CityMunicipalityRepository;

public class RegisterCityMunicipalityUseCase {
    private final CityMunicipalityRepository repository;
    public RegisterCityMunicipalityUseCase(CityMunicipalityRepository repository) { this.repository = repository; }

    public CityMunicipalityResponse execute(RegisterCityMunicipalityCommand command) {
        CityMunicipality aggregate = CityMunicipality.register(
                command.nameCity(),
                command.codeCity(),
                command.description(),
                command.active(),
                command.regionId());
        CityMunicipality saved = repository.save(aggregate);
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
