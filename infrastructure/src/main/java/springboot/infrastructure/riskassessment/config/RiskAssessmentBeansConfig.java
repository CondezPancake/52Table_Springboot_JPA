package springboot.infrastructure.riskassessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.riskassessment.usecase.*;
import springboot.domain.riskassessment.port.repository.RiskAssessmentRepository;
import springboot.infrastructure.riskassessment.adapters.out.persistence.mappers.RiskAssessmentPersistenceMapper;
import springboot.infrastructure.riskassessment.adapters.out.persistence.repositories.RiskAssessmentJpaRepository;
import springboot.infrastructure.riskassessment.adapters.out.persistence.repositories.RiskAssessmentRepositoryAdapter;

@Configuration
public class RiskAssessmentBeansConfig {
    @Bean public RiskAssessmentPersistenceMapper riskassessmentPersistenceMapper() { return new RiskAssessmentPersistenceMapper(); }
    @Bean public RiskAssessmentRepository riskassessmentRepository(RiskAssessmentJpaRepository repository, RiskAssessmentPersistenceMapper mapper) {
        return new RiskAssessmentRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterRiskAssessmentUseCase registerRiskAssessmentUseCase(RiskAssessmentRepository r) { return new RegisterRiskAssessmentUseCase(r); }
    @Bean public GetRiskAssessmentByIdUseCase getRiskAssessmentByIdUseCase(RiskAssessmentRepository r) { return new GetRiskAssessmentByIdUseCase(r); }
    @Bean public ListRiskAssessmentUseCase listRiskAssessmentUseCase(RiskAssessmentRepository r) { return new ListRiskAssessmentUseCase(r); }
    @Bean public UpdateRiskAssessmentUseCase updateRiskAssessmentUseCase(RiskAssessmentRepository r) { return new UpdateRiskAssessmentUseCase(r); }
    @Bean public DeleteRiskAssessmentUseCase deleteRiskAssessmentUseCase(RiskAssessmentRepository r) { return new DeleteRiskAssessmentUseCase(r); }
}
