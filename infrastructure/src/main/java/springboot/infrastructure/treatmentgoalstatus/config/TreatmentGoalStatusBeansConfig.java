package springboot.infrastructure.treatmentgoalstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.treatmentgoalstatus.usecase.*;
import springboot.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;
import springboot.infrastructure.treatmentgoalstatus.adapters.out.persistence.mappers.TreatmentGoalStatusPersistenceMapper;
import springboot.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories.TreatmentGoalStatusJpaRepository;
import springboot.infrastructure.treatmentgoalstatus.adapters.out.persistence.repositories.TreatmentGoalStatusRepositoryAdapter;

@Configuration
public class TreatmentGoalStatusBeansConfig {
    @Bean public TreatmentGoalStatusPersistenceMapper treatmentgoalstatusPersistenceMapper() { return new TreatmentGoalStatusPersistenceMapper(); }
    @Bean public TreatmentGoalStatusRepository treatmentgoalstatusRepository(TreatmentGoalStatusJpaRepository repository, TreatmentGoalStatusPersistenceMapper mapper) {
        return new TreatmentGoalStatusRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterTreatmentGoalStatusUseCase registerTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository r) { return new RegisterTreatmentGoalStatusUseCase(r); }
    @Bean public GetTreatmentGoalStatusByIdUseCase getTreatmentGoalStatusByIdUseCase(TreatmentGoalStatusRepository r) { return new GetTreatmentGoalStatusByIdUseCase(r); }
    @Bean public ListTreatmentGoalStatusUseCase listTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository r) { return new ListTreatmentGoalStatusUseCase(r); }
    @Bean public UpdateTreatmentGoalStatusUseCase updateTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository r) { return new UpdateTreatmentGoalStatusUseCase(r); }
    @Bean public DeleteTreatmentGoalStatusUseCase deleteTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository r) { return new DeleteTreatmentGoalStatusUseCase(r); }
}
