package springboot.infrastructure.encountermodality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.encountermodality.usecase.*;
import springboot.domain.encountermodality.port.repository.EncounterModalityRepository;
import springboot.infrastructure.encountermodality.adapters.out.persistence.mappers.EncounterModalityPersistenceMapper;
import springboot.infrastructure.encountermodality.adapters.out.persistence.repositories.EncounterModalityJpaRepository;
import springboot.infrastructure.encountermodality.adapters.out.persistence.repositories.EncounterModalityRepositoryAdapter;

@Configuration
public class EncounterModalityBeansConfig {
    @Bean public EncounterModalityPersistenceMapper encountermodalityPersistenceMapper() { return new EncounterModalityPersistenceMapper(); }
    @Bean public EncounterModalityRepository encountermodalityRepository(EncounterModalityJpaRepository repository, EncounterModalityPersistenceMapper mapper) {
        return new EncounterModalityRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterEncounterModalityUseCase registerEncounterModalityUseCase(EncounterModalityRepository r) { return new RegisterEncounterModalityUseCase(r); }
    @Bean public GetEncounterModalityByIdUseCase getEncounterModalityByIdUseCase(EncounterModalityRepository r) { return new GetEncounterModalityByIdUseCase(r); }
    @Bean public ListEncounterModalityUseCase listEncounterModalityUseCase(EncounterModalityRepository r) { return new ListEncounterModalityUseCase(r); }
    @Bean public UpdateEncounterModalityUseCase updateEncounterModalityUseCase(EncounterModalityRepository r) { return new UpdateEncounterModalityUseCase(r); }
    @Bean public DeleteEncounterModalityUseCase deleteEncounterModalityUseCase(EncounterModalityRepository r) { return new DeleteEncounterModalityUseCase(r); }
}
