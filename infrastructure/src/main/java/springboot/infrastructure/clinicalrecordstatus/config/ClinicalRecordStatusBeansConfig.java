package springboot.infrastructure.clinicalrecordstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.clinicalrecordstatus.usecase.*;
import springboot.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;
import springboot.infrastructure.clinicalrecordstatus.adapters.out.persistence.mappers.ClinicalRecordStatusPersistenceMapper;
import springboot.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories.ClinicalRecordStatusJpaRepository;
import springboot.infrastructure.clinicalrecordstatus.adapters.out.persistence.repositories.ClinicalRecordStatusRepositoryAdapter;

@Configuration
public class ClinicalRecordStatusBeansConfig {
    @Bean public ClinicalRecordStatusPersistenceMapper clinicalrecordstatusPersistenceMapper() { return new ClinicalRecordStatusPersistenceMapper(); }
    @Bean public ClinicalRecordStatusRepository clinicalrecordstatusRepository(ClinicalRecordStatusJpaRepository repository, ClinicalRecordStatusPersistenceMapper mapper) {
        return new ClinicalRecordStatusRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterClinicalRecordStatusUseCase registerClinicalRecordStatusUseCase(ClinicalRecordStatusRepository r) { return new RegisterClinicalRecordStatusUseCase(r); }
    @Bean public GetClinicalRecordStatusByIdUseCase getClinicalRecordStatusByIdUseCase(ClinicalRecordStatusRepository r) { return new GetClinicalRecordStatusByIdUseCase(r); }
    @Bean public ListClinicalRecordStatusUseCase listClinicalRecordStatusUseCase(ClinicalRecordStatusRepository r) { return new ListClinicalRecordStatusUseCase(r); }
    @Bean public UpdateClinicalRecordStatusUseCase updateClinicalRecordStatusUseCase(ClinicalRecordStatusRepository r) { return new UpdateClinicalRecordStatusUseCase(r); }
    @Bean public DeleteClinicalRecordStatusUseCase deleteClinicalRecordStatusUseCase(ClinicalRecordStatusRepository r) { return new DeleteClinicalRecordStatusUseCase(r); }
}
