package springboot.infrastructure.country.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.country.usecase.*;
import springboot.domain.country.port.repository.CountryRepository;
import springboot.infrastructure.country.adapters.out.persistence.mappers.CountryPersistenceMapper;
import springboot.infrastructure.country.adapters.out.persistence.repositories.CountryJpaRepository;
import springboot.infrastructure.country.adapters.out.persistence.repositories.CountryRepositoryAdapter;

@Configuration
public class CountryBeansConfig {
    @Bean public CountryPersistenceMapper countryPersistenceMapper() { return new CountryPersistenceMapper(); }
    @Bean public CountryRepository countryRepository(CountryJpaRepository repository, CountryPersistenceMapper mapper) {
        return new CountryRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterCountryUseCase registerCountryUseCase(CountryRepository r) { return new RegisterCountryUseCase(r); }
    @Bean public GetCountryByIdUseCase getCountryByIdUseCase(CountryRepository r) { return new GetCountryByIdUseCase(r); }
    @Bean public ListCountryUseCase listCountryUseCase(CountryRepository r) { return new ListCountryUseCase(r); }
    @Bean public UpdateCountryUseCase updateCountryUseCase(CountryRepository r) { return new UpdateCountryUseCase(r); }
    @Bean public DeleteCountryUseCase deleteCountryUseCase(CountryRepository r) { return new DeleteCountryUseCase(r); }
}
