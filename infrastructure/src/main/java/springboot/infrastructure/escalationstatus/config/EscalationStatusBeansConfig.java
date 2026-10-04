package springboot.infrastructure.escalationstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.escalationstatus.usecase.*;
import springboot.domain.escalationstatus.port.repository.EscalationStatusRepository;
import springboot.infrastructure.escalationstatus.adapters.out.persistence.mappers.EscalationStatusPersistenceMapper;
import springboot.infrastructure.escalationstatus.adapters.out.persistence.repositories.EscalationStatusJpaRepository;
import springboot.infrastructure.escalationstatus.adapters.out.persistence.repositories.EscalationStatusRepositoryAdapter;

@Configuration
public class EscalationStatusBeansConfig {
    @Bean public EscalationStatusPersistenceMapper escalationstatusPersistenceMapper() { return new EscalationStatusPersistenceMapper(); }
    @Bean public EscalationStatusRepository escalationstatusRepository(EscalationStatusJpaRepository repository, EscalationStatusPersistenceMapper mapper) {
        return new EscalationStatusRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterEscalationStatusUseCase registerEscalationStatusUseCase(EscalationStatusRepository r) { return new RegisterEscalationStatusUseCase(r); }
    @Bean public GetEscalationStatusByIdUseCase getEscalationStatusByIdUseCase(EscalationStatusRepository r) { return new GetEscalationStatusByIdUseCase(r); }
    @Bean public ListEscalationStatusUseCase listEscalationStatusUseCase(EscalationStatusRepository r) { return new ListEscalationStatusUseCase(r); }
    @Bean public UpdateEscalationStatusUseCase updateEscalationStatusUseCase(EscalationStatusRepository r) { return new UpdateEscalationStatusUseCase(r); }
    @Bean public DeleteEscalationStatusUseCase deleteEscalationStatusUseCase(EscalationStatusRepository r) { return new DeleteEscalationStatusUseCase(r); }
}
