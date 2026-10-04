package springboot.infrastructure.risklevel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.risklevel.usecase.*;
import springboot.domain.risklevel.port.repository.RiskLevelRepository;
import springboot.infrastructure.risklevel.adapters.out.persistence.mappers.RiskLevelPersistenceMapper;
import springboot.infrastructure.risklevel.adapters.out.persistence.repositories.RiskLevelJpaRepository;
import springboot.infrastructure.risklevel.adapters.out.persistence.repositories.RiskLevelRepositoryAdapter;

@Configuration
public class RiskLevelBeansConfig {
    @Bean public RiskLevelPersistenceMapper risklevelPersistenceMapper() { return new RiskLevelPersistenceMapper(); }
    @Bean public RiskLevelRepository risklevelRepository(RiskLevelJpaRepository repository, RiskLevelPersistenceMapper mapper) {
        return new RiskLevelRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterRiskLevelUseCase registerRiskLevelUseCase(RiskLevelRepository r) { return new RegisterRiskLevelUseCase(r); }
    @Bean public GetRiskLevelByIdUseCase getRiskLevelByIdUseCase(RiskLevelRepository r) { return new GetRiskLevelByIdUseCase(r); }
    @Bean public ListRiskLevelUseCase listRiskLevelUseCase(RiskLevelRepository r) { return new ListRiskLevelUseCase(r); }
    @Bean public UpdateRiskLevelUseCase updateRiskLevelUseCase(RiskLevelRepository r) { return new UpdateRiskLevelUseCase(r); }
    @Bean public DeleteRiskLevelUseCase deleteRiskLevelUseCase(RiskLevelRepository r) { return new DeleteRiskLevelUseCase(r); }
}
