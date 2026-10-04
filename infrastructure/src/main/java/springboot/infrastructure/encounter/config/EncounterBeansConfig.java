package springboot.infrastructure.encounter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.encounter.usecase.*;
import springboot.domain.encounter.port.repository.EncounterRepository;
import springboot.infrastructure.encounter.adapters.out.persistence.mappers.EncounterPersistenceMapper;
import springboot.infrastructure.encounter.adapters.out.persistence.repositories.EncounterJpaRepository;
import springboot.infrastructure.encounter.adapters.out.persistence.repositories.EncounterRepositoryAdapter;

@Configuration
public class EncounterBeansConfig {
    @Bean public EncounterPersistenceMapper encounterPersistenceMapper() { return new EncounterPersistenceMapper(); }
    @Bean public EncounterRepository encounterRepository(EncounterJpaRepository repository, EncounterPersistenceMapper mapper) {
        return new EncounterRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterEncounterUseCase registerEncounterUseCase(EncounterRepository r) { return new RegisterEncounterUseCase(r); }
    @Bean public GetEncounterByIdUseCase getEncounterByIdUseCase(EncounterRepository r) { return new GetEncounterByIdUseCase(r); }
    @Bean public ListEncounterUseCase listEncounterUseCase(EncounterRepository r) { return new ListEncounterUseCase(r); }
    @Bean public UpdateEncounterUseCase updateEncounterUseCase(EncounterRepository r) { return new UpdateEncounterUseCase(r); }
    @Bean public DeleteEncounterUseCase deleteEncounterUseCase(EncounterRepository r) { return new DeleteEncounterUseCase(r); }
}
