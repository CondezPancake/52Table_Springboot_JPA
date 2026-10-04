package springboot.infrastructure.treatmentstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.treatmentstatus.usecase.*;
import springboot.domain.treatmentstatus.port.repository.TreatmentStatusRepository;
import springboot.infrastructure.treatmentstatus.adapters.out.persistence.mappers.TreatmentStatusPersistenceMapper;
import springboot.infrastructure.treatmentstatus.adapters.out.persistence.repositories.TreatmentStatusJpaRepository;
import springboot.infrastructure.treatmentstatus.adapters.out.persistence.repositories.TreatmentStatusRepositoryAdapter;

@Configuration
public class TreatmentStatusBeansConfig {
    @Bean public TreatmentStatusPersistenceMapper treatmentstatusPersistenceMapper() { return new TreatmentStatusPersistenceMapper(); }
    @Bean public TreatmentStatusRepository treatmentstatusRepository(TreatmentStatusJpaRepository repository, TreatmentStatusPersistenceMapper mapper) {
        return new TreatmentStatusRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterTreatmentStatusUseCase registerTreatmentStatusUseCase(TreatmentStatusRepository r) { return new RegisterTreatmentStatusUseCase(r); }
    @Bean public GetTreatmentStatusByIdUseCase getTreatmentStatusByIdUseCase(TreatmentStatusRepository r) { return new GetTreatmentStatusByIdUseCase(r); }
    @Bean public ListTreatmentStatusUseCase listTreatmentStatusUseCase(TreatmentStatusRepository r) { return new ListTreatmentStatusUseCase(r); }
    @Bean public UpdateTreatmentStatusUseCase updateTreatmentStatusUseCase(TreatmentStatusRepository r) { return new UpdateTreatmentStatusUseCase(r); }
    @Bean public DeleteTreatmentStatusUseCase deleteTreatmentStatusUseCase(TreatmentStatusRepository r) { return new DeleteTreatmentStatusUseCase(r); }
}
