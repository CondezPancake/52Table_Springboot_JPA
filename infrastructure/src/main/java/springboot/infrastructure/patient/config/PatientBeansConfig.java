package springboot.infrastructure.patient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.patient.usecase.*;
import springboot.domain.patient.port.repository.PatientRepository;
import springboot.infrastructure.patient.adapters.out.persistence.mappers.PatientPersistenceMapper;
import springboot.infrastructure.patient.adapters.out.persistence.repositories.PatientJpaRepository;
import springboot.infrastructure.patient.adapters.out.persistence.repositories.PatientRepositoryAdapter;

@Configuration
public class PatientBeansConfig {
    @Bean public PatientPersistenceMapper patientPersistenceMapper() { return new PatientPersistenceMapper(); }
    @Bean public PatientRepository patientRepository(PatientJpaRepository repository, PatientPersistenceMapper mapper) {
        return new PatientRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterPatientUseCase registerPatientUseCase(PatientRepository r) { return new RegisterPatientUseCase(r); }
    @Bean public GetPatientByIdUseCase getPatientByIdUseCase(PatientRepository r) { return new GetPatientByIdUseCase(r); }
    @Bean public ListPatientUseCase listPatientUseCase(PatientRepository r) { return new ListPatientUseCase(r); }
    @Bean public UpdatePatientUseCase updatePatientUseCase(PatientRepository r) { return new UpdatePatientUseCase(r); }
    @Bean public DeletePatientUseCase deletePatientUseCase(PatientRepository r) { return new DeletePatientUseCase(r); }
}
