package springboot.application.country.usecase;

import java.util.List;

import springboot.application.country.dto.CountryResponse;
import springboot.domain.country.port.repository.CountryRepository;

public class ListCountryUseCase {
    private final CountryRepository repository;
    public ListCountryUseCase(CountryRepository repository) { this.repository = repository; }

    public List<CountryResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new CountryResponse(
                                aggregate.id().value(),
                                aggregate.nameCountry(),
                                aggregate.codeCountry(),
                                aggregate.description(),
                                aggregate.active(),
                                aggregate.telephonePrefix(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
