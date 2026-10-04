package springboot.infrastructure.stateregion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.stateregion.usecase.*;
import springboot.domain.stateregion.port.repository.StateRegionRepository;
import springboot.infrastructure.stateregion.adapters.out.persistence.mappers.StateRegionPersistenceMapper;
import springboot.infrastructure.stateregion.adapters.out.persistence.repositories.StateRegionJpaRepository;
import springboot.infrastructure.stateregion.adapters.out.persistence.repositories.StateRegionRepositoryAdapter;

@Configuration
public class StateRegionBeansConfig {
    @Bean public StateRegionPersistenceMapper stateregionPersistenceMapper() { return new StateRegionPersistenceMapper(); }
    @Bean public StateRegionRepository stateregionRepository(StateRegionJpaRepository repository, StateRegionPersistenceMapper mapper) {
        return new StateRegionRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterStateRegionUseCase registerStateRegionUseCase(StateRegionRepository r) { return new RegisterStateRegionUseCase(r); }
    @Bean public GetStateRegionByIdUseCase getStateRegionByIdUseCase(StateRegionRepository r) { return new GetStateRegionByIdUseCase(r); }
    @Bean public ListStateRegionUseCase listStateRegionUseCase(StateRegionRepository r) { return new ListStateRegionUseCase(r); }
    @Bean public UpdateStateRegionUseCase updateStateRegionUseCase(StateRegionRepository r) { return new UpdateStateRegionUseCase(r); }
    @Bean public DeleteStateRegionUseCase deleteStateRegionUseCase(StateRegionRepository r) { return new DeleteStateRegionUseCase(r); }
}
