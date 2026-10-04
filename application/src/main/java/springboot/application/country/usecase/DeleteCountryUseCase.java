package springboot.application.country.usecase;

import java.time.LocalDateTime;

import springboot.application.country.exception.CountryNotFoundApplicationException;
import springboot.domain.country.event.CountryDeletedEvent;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.domain.country.port.repository.CountryRepository;

public class DeleteCountryUseCase {
    private final CountryRepository repository;
    public DeleteCountryUseCase(CountryRepository repository) { this.repository = repository; }

    public CountryDeletedEvent execute(CountryId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CountryNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new CountryDeletedEvent(id, LocalDateTime.now());
    }
}
