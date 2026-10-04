package springboot.infrastructure.encounterstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.encounterstatus.usecase.*;
import springboot.domain.encounterstatus.port.repository.EncounterStatusRepository;
import springboot.infrastructure.encounterstatus.adapters.out.persistence.mappers.EncounterStatusPersistenceMapper;
import springboot.infrastructure.encounterstatus.adapters.out.persistence.repositories.EncounterStatusJpaRepository;
import springboot.infrastructure.encounterstatus.adapters.out.persistence.repositories.EncounterStatusRepositoryAdapter;

@Configuration
public class EncounterStatusBeansConfig {
    @Bean public EncounterStatusPersistenceMapper encounterstatusPersistenceMapper() { return new EncounterStatusPersistenceMapper(); }
    @Bean public EncounterStatusRepository encounterstatusRepository(EncounterStatusJpaRepository repository, EncounterStatusPersistenceMapper mapper) {
        return new EncounterStatusRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterEncounterStatusUseCase registerEncounterStatusUseCase(EncounterStatusRepository r) { return new RegisterEncounterStatusUseCase(r); }
    @Bean public GetEncounterStatusByIdUseCase getEncounterStatusByIdUseCase(EncounterStatusRepository r) { return new GetEncounterStatusByIdUseCase(r); }
    @Bean public ListEncounterStatusUseCase listEncounterStatusUseCase(EncounterStatusRepository r) { return new ListEncounterStatusUseCase(r); }
    @Bean public UpdateEncounterStatusUseCase updateEncounterStatusUseCase(EncounterStatusRepository r) { return new UpdateEncounterStatusUseCase(r); }
    @Bean public DeleteEncounterStatusUseCase deleteEncounterStatusUseCase(EncounterStatusRepository r) { return new DeleteEncounterStatusUseCase(r); }
}
