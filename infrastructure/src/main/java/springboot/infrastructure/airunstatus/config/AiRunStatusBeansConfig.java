package springboot.infrastructure.airunstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.airunstatus.usecase.*;
import springboot.domain.airunstatus.port.repository.AiRunStatusRepository;
import springboot.infrastructure.airunstatus.adapters.out.persistence.mappers.AiRunStatusPersistenceMapper;
import springboot.infrastructure.airunstatus.adapters.out.persistence.repositories.AiRunStatusJpaRepository;
import springboot.infrastructure.airunstatus.adapters.out.persistence.repositories.AiRunStatusRepositoryAdapter;

@Configuration
public class AiRunStatusBeansConfig {
    @Bean public AiRunStatusPersistenceMapper airunstatusPersistenceMapper() { return new AiRunStatusPersistenceMapper(); }
    @Bean public AiRunStatusRepository airunstatusRepository(AiRunStatusJpaRepository repository, AiRunStatusPersistenceMapper mapper) {
        return new AiRunStatusRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterAiRunStatusUseCase registerAiRunStatusUseCase(AiRunStatusRepository r) { return new RegisterAiRunStatusUseCase(r); }
    @Bean public GetAiRunStatusByIdUseCase getAiRunStatusByIdUseCase(AiRunStatusRepository r) { return new GetAiRunStatusByIdUseCase(r); }
    @Bean public ListAiRunStatusUseCase listAiRunStatusUseCase(AiRunStatusRepository r) { return new ListAiRunStatusUseCase(r); }
    @Bean public UpdateAiRunStatusUseCase updateAiRunStatusUseCase(AiRunStatusRepository r) { return new UpdateAiRunStatusUseCase(r); }
    @Bean public DeleteAiRunStatusUseCase deleteAiRunStatusUseCase(AiRunStatusRepository r) { return new DeleteAiRunStatusUseCase(r); }
}
