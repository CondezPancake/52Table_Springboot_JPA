package springboot.infrastructure.citymunicipality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.citymunicipality.usecase.*;
import springboot.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import springboot.infrastructure.citymunicipality.adapters.out.persistence.mappers.CityMunicipalityPersistenceMapper;
import springboot.infrastructure.citymunicipality.adapters.out.persistence.repositories.CityMunicipalityJpaRepository;
import springboot.infrastructure.citymunicipality.adapters.out.persistence.repositories.CityMunicipalityRepositoryAdapter;

@Configuration
public class CityMunicipalityBeansConfig {
    @Bean public CityMunicipalityPersistenceMapper citymunicipalityPersistenceMapper() { return new CityMunicipalityPersistenceMapper(); }
    @Bean public CityMunicipalityRepository citymunicipalityRepository(CityMunicipalityJpaRepository repository, CityMunicipalityPersistenceMapper mapper) {
        return new CityMunicipalityRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterCityMunicipalityUseCase registerCityMunicipalityUseCase(CityMunicipalityRepository r) { return new RegisterCityMunicipalityUseCase(r); }
    @Bean public GetCityMunicipalityByIdUseCase getCityMunicipalityByIdUseCase(CityMunicipalityRepository r) { return new GetCityMunicipalityByIdUseCase(r); }
    @Bean public ListCityMunicipalityUseCase listCityMunicipalityUseCase(CityMunicipalityRepository r) { return new ListCityMunicipalityUseCase(r); }
    @Bean public UpdateCityMunicipalityUseCase updateCityMunicipalityUseCase(CityMunicipalityRepository r) { return new UpdateCityMunicipalityUseCase(r); }
    @Bean public DeleteCityMunicipalityUseCase deleteCityMunicipalityUseCase(CityMunicipalityRepository r) { return new DeleteCityMunicipalityUseCase(r); }
}
