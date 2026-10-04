package springboot.infrastructure.encountertype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.encountertype.usecase.*;
import springboot.domain.encountertype.port.repository.EncounterTypeRepository;
import springboot.infrastructure.encountertype.adapters.out.persistence.mappers.EncounterTypePersistenceMapper;
import springboot.infrastructure.encountertype.adapters.out.persistence.repositories.EncounterTypeJpaRepository;
import springboot.infrastructure.encountertype.adapters.out.persistence.repositories.EncounterTypeRepositoryAdapter;

@Configuration
public class EncounterTypeBeansConfig {
    @Bean public EncounterTypePersistenceMapper encountertypePersistenceMapper() { return new EncounterTypePersistenceMapper(); }
    @Bean public EncounterTypeRepository encountertypeRepository(EncounterTypeJpaRepository repository, EncounterTypePersistenceMapper mapper) {
        return new EncounterTypeRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterEncounterTypeUseCase registerEncounterTypeUseCase(EncounterTypeRepository r) { return new RegisterEncounterTypeUseCase(r); }
    @Bean public GetEncounterTypeByIdUseCase getEncounterTypeByIdUseCase(EncounterTypeRepository r) { return new GetEncounterTypeByIdUseCase(r); }
    @Bean public ListEncounterTypeUseCase listEncounterTypeUseCase(EncounterTypeRepository r) { return new ListEncounterTypeUseCase(r); }
    @Bean public UpdateEncounterTypeUseCase updateEncounterTypeUseCase(EncounterTypeRepository r) { return new UpdateEncounterTypeUseCase(r); }
    @Bean public DeleteEncounterTypeUseCase deleteEncounterTypeUseCase(EncounterTypeRepository r) { return new DeleteEncounterTypeUseCase(r); }
}
