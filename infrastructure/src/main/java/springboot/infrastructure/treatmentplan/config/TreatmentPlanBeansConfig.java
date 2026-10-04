package springboot.infrastructure.treatmentplan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.treatmentplan.usecase.*;
import springboot.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import springboot.infrastructure.treatmentplan.adapters.out.persistence.mappers.TreatmentPlanPersistenceMapper;
import springboot.infrastructure.treatmentplan.adapters.out.persistence.repositories.TreatmentPlanJpaRepository;
import springboot.infrastructure.treatmentplan.adapters.out.persistence.repositories.TreatmentPlanRepositoryAdapter;

@Configuration
public class TreatmentPlanBeansConfig {
    @Bean public TreatmentPlanPersistenceMapper treatmentplanPersistenceMapper() { return new TreatmentPlanPersistenceMapper(); }
    @Bean public TreatmentPlanRepository treatmentplanRepository(TreatmentPlanJpaRepository repository, TreatmentPlanPersistenceMapper mapper) {
        return new TreatmentPlanRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterTreatmentPlanUseCase registerTreatmentPlanUseCase(TreatmentPlanRepository r) { return new RegisterTreatmentPlanUseCase(r); }
    @Bean public GetTreatmentPlanByIdUseCase getTreatmentPlanByIdUseCase(TreatmentPlanRepository r) { return new GetTreatmentPlanByIdUseCase(r); }
    @Bean public ListTreatmentPlanUseCase listTreatmentPlanUseCase(TreatmentPlanRepository r) { return new ListTreatmentPlanUseCase(r); }
    @Bean public UpdateTreatmentPlanUseCase updateTreatmentPlanUseCase(TreatmentPlanRepository r) { return new UpdateTreatmentPlanUseCase(r); }
    @Bean public DeleteTreatmentPlanUseCase deleteTreatmentPlanUseCase(TreatmentPlanRepository r) { return new DeleteTreatmentPlanUseCase(r); }
}
