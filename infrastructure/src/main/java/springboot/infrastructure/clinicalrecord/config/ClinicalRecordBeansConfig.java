package springboot.infrastructure.clinicalrecord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.clinicalrecord.usecase.*;
import springboot.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import springboot.infrastructure.clinicalrecord.adapters.out.persistence.mappers.ClinicalRecordPersistenceMapper;
import springboot.infrastructure.clinicalrecord.adapters.out.persistence.repositories.ClinicalRecordJpaRepository;
import springboot.infrastructure.clinicalrecord.adapters.out.persistence.repositories.ClinicalRecordRepositoryAdapter;

@Configuration
public class ClinicalRecordBeansConfig {
    @Bean public ClinicalRecordPersistenceMapper clinicalrecordPersistenceMapper() { return new ClinicalRecordPersistenceMapper(); }
    @Bean public ClinicalRecordRepository clinicalrecordRepository(ClinicalRecordJpaRepository repository, ClinicalRecordPersistenceMapper mapper) {
        return new ClinicalRecordRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterClinicalRecordUseCase registerClinicalRecordUseCase(ClinicalRecordRepository r) { return new RegisterClinicalRecordUseCase(r); }
    @Bean public GetClinicalRecordByIdUseCase getClinicalRecordByIdUseCase(ClinicalRecordRepository r) { return new GetClinicalRecordByIdUseCase(r); }
    @Bean public ListClinicalRecordUseCase listClinicalRecordUseCase(ClinicalRecordRepository r) { return new ListClinicalRecordUseCase(r); }
    @Bean public UpdateClinicalRecordUseCase updateClinicalRecordUseCase(ClinicalRecordRepository r) { return new UpdateClinicalRecordUseCase(r); }
    @Bean public DeleteClinicalRecordUseCase deleteClinicalRecordUseCase(ClinicalRecordRepository r) { return new DeleteClinicalRecordUseCase(r); }
}
