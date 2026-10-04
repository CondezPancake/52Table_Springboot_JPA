package springboot.application.country.usecase;

import springboot.application.country.command.UpdateCountryCommand;
import springboot.application.country.dto.CountryResponse;
import springboot.application.country.exception.CountryNotFoundApplicationException;
import springboot.domain.country.port.repository.CountryRepository;

public class UpdateCountryUseCase {
    private final CountryRepository repository;
    public UpdateCountryUseCase(CountryRepository repository) { this.repository = repository; }

    public CountryResponse execute(UpdateCountryCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new CountryNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.nameCountry(),
                command.codeCountry(),
                command.description(),
                command.active(),
                command.telephonePrefix());
        var saved = repository.save(aggregate);
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
