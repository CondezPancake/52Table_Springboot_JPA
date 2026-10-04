package springboot.application.country.usecase;

import springboot.application.country.command.RegisterCountryCommand;
import springboot.application.country.dto.CountryResponse;
import springboot.domain.country.model.aggregate.Country;
import springboot.domain.country.port.repository.CountryRepository;

public class RegisterCountryUseCase {
    private final CountryRepository repository;
    public RegisterCountryUseCase(CountryRepository repository) { this.repository = repository; }

    public CountryResponse execute(RegisterCountryCommand command) {
        Country aggregate = Country.register(
                command.nameCountry(),
                command.codeCountry(),
                command.description(),
                command.active(),
                command.telephonePrefix());
        Country saved = repository.save(aggregate);
        return new CountryResponse(
                saved.id().value(),
                saved.nameCountry(),
                saved.codeCountry(),
                saved.description(),
                saved.active(),
                saved.telephonePrefix(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
