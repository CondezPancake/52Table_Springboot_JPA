package springboot.infrastructure.treatmentgoal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.treatmentgoal.usecase.*;
import springboot.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import springboot.infrastructure.treatmentgoal.adapters.out.persistence.mappers.TreatmentGoalPersistenceMapper;
import springboot.infrastructure.treatmentgoal.adapters.out.persistence.repositories.TreatmentGoalJpaRepository;
import springboot.infrastructure.treatmentgoal.adapters.out.persistence.repositories.TreatmentGoalRepositoryAdapter;

@Configuration
public class TreatmentGoalBeansConfig {
    @Bean public TreatmentGoalPersistenceMapper treatmentgoalPersistenceMapper() { return new TreatmentGoalPersistenceMapper(); }
    @Bean public TreatmentGoalRepository treatmentgoalRepository(TreatmentGoalJpaRepository repository, TreatmentGoalPersistenceMapper mapper) {
        return new TreatmentGoalRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterTreatmentGoalUseCase registerTreatmentGoalUseCase(TreatmentGoalRepository r) { return new RegisterTreatmentGoalUseCase(r); }
    @Bean public GetTreatmentGoalByIdUseCase getTreatmentGoalByIdUseCase(TreatmentGoalRepository r) { return new GetTreatmentGoalByIdUseCase(r); }
    @Bean public ListTreatmentGoalUseCase listTreatmentGoalUseCase(TreatmentGoalRepository r) { return new ListTreatmentGoalUseCase(r); }
    @Bean public UpdateTreatmentGoalUseCase updateTreatmentGoalUseCase(TreatmentGoalRepository r) { return new UpdateTreatmentGoalUseCase(r); }
    @Bean public DeleteTreatmentGoalUseCase deleteTreatmentGoalUseCase(TreatmentGoalRepository r) { return new DeleteTreatmentGoalUseCase(r); }
}
