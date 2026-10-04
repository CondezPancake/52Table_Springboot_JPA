package springboot.infrastructure.aimodel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.aimodel.usecase.*;
import springboot.domain.aimodel.port.repository.AiModelRepository;
import springboot.infrastructure.aimodel.adapters.out.persistence.mappers.AiModelPersistenceMapper;
import springboot.infrastructure.aimodel.adapters.out.persistence.repositories.AiModelJpaRepository;
import springboot.infrastructure.aimodel.adapters.out.persistence.repositories.AiModelRepositoryAdapter;

@Configuration
public class AiModelBeansConfig {
    @Bean public AiModelPersistenceMapper aimodelPersistenceMapper() { return new AiModelPersistenceMapper(); }
    @Bean public AiModelRepository aimodelRepository(AiModelJpaRepository repository, AiModelPersistenceMapper mapper) {
        return new AiModelRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterAiModelUseCase registerAiModelUseCase(AiModelRepository r) { return new RegisterAiModelUseCase(r); }
    @Bean public GetAiModelByIdUseCase getAiModelByIdUseCase(AiModelRepository r) { return new GetAiModelByIdUseCase(r); }
    @Bean public ListAiModelUseCase listAiModelUseCase(AiModelRepository r) { return new ListAiModelUseCase(r); }
    @Bean public UpdateAiModelUseCase updateAiModelUseCase(AiModelRepository r) { return new UpdateAiModelUseCase(r); }
    @Bean public DeleteAiModelUseCase deleteAiModelUseCase(AiModelRepository r) { return new DeleteAiModelUseCase(r); }
}
