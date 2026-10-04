package springboot.application.country.usecase;

import springboot.application.country.dto.CountryResponse;
import springboot.application.country.exception.CountryNotFoundApplicationException;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.domain.country.port.repository.CountryRepository;

public class GetCountryByIdUseCase {
    private final CountryRepository repository;
    public GetCountryByIdUseCase(CountryRepository repository) { this.repository = repository; }

    public CountryResponse execute(CountryId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new CountryNotFoundApplicationException(id.value().toString()));
        return new CountryResponse(
                aggregate.id().value(),
                aggregate.nameCountry(),
                aggregate.codeCountry(),
                aggregate.description(),
                aggregate.active(),
                aggregate.telephonePrefix(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
