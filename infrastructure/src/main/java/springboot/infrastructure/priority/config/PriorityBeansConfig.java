package springboot.infrastructure.priority.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.priority.usecase.*;
import springboot.domain.priority.port.repository.PriorityRepository;
import springboot.infrastructure.priority.adapters.out.persistence.mappers.PriorityPersistenceMapper;
import springboot.infrastructure.priority.adapters.out.persistence.repositories.PriorityJpaRepository;
import springboot.infrastructure.priority.adapters.out.persistence.repositories.PriorityRepositoryAdapter;

@Configuration
public class PriorityBeansConfig {
    @Bean public PriorityPersistenceMapper priorityPersistenceMapper() { return new PriorityPersistenceMapper(); }
    @Bean public PriorityRepository priorityRepository(PriorityJpaRepository repository, PriorityPersistenceMapper mapper) {
        return new PriorityRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterPriorityUseCase registerPriorityUseCase(PriorityRepository r) { return new RegisterPriorityUseCase(r); }
    @Bean public GetPriorityByIdUseCase getPriorityByIdUseCase(PriorityRepository r) { return new GetPriorityByIdUseCase(r); }
    @Bean public ListPriorityUseCase listPriorityUseCase(PriorityRepository r) { return new ListPriorityUseCase(r); }
    @Bean public UpdatePriorityUseCase updatePriorityUseCase(PriorityRepository r) { return new UpdatePriorityUseCase(r); }
    @Bean public DeletePriorityUseCase deletePriorityUseCase(PriorityRepository r) { return new DeletePriorityUseCase(r); }
}
