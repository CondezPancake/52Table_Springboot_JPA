package springboot.infrastructure.gender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.gender.usecase.*;
import springboot.domain.gender.port.repository.GenderRepository;
import springboot.infrastructure.gender.adapters.out.persistence.mappers.GenderPersistenceMapper;
import springboot.infrastructure.gender.adapters.out.persistence.repositories.GenderJpaRepository;
import springboot.infrastructure.gender.adapters.out.persistence.repositories.GenderRepositoryAdapter;

@Configuration
public class GenderBeansConfig {
    @Bean public GenderPersistenceMapper genderPersistenceMapper() { return new GenderPersistenceMapper(); }
    @Bean public GenderRepository genderRepository(GenderJpaRepository repository, GenderPersistenceMapper mapper) {
        return new GenderRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterGenderUseCase registerGenderUseCase(GenderRepository r) { return new RegisterGenderUseCase(r); }
    @Bean public GetGenderByIdUseCase getGenderByIdUseCase(GenderRepository r) { return new GetGenderByIdUseCase(r); }
    @Bean public ListGenderUseCase listGenderUseCase(GenderRepository r) { return new ListGenderUseCase(r); }
    @Bean public UpdateGenderUseCase updateGenderUseCase(GenderRepository r) { return new UpdateGenderUseCase(r); }
    @Bean public DeleteGenderUseCase deleteGenderUseCase(GenderRepository r) { return new DeleteGenderUseCase(r); }
}
