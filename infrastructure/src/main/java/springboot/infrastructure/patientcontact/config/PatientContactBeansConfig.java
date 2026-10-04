package springboot.infrastructure.patientcontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.patientcontact.usecase.*;
import springboot.domain.patientcontact.port.repository.PatientContactRepository;
import springboot.infrastructure.patientcontact.adapters.out.persistence.mappers.PatientContactPersistenceMapper;
import springboot.infrastructure.patientcontact.adapters.out.persistence.repositories.PatientContactJpaRepository;
import springboot.infrastructure.patientcontact.adapters.out.persistence.repositories.PatientContactRepositoryAdapter;

@Configuration
public class PatientContactBeansConfig {
    @Bean public PatientContactPersistenceMapper patientcontactPersistenceMapper() { return new PatientContactPersistenceMapper(); }
    @Bean public PatientContactRepository patientcontactRepository(PatientContactJpaRepository repository, PatientContactPersistenceMapper mapper) {
        return new PatientContactRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterPatientContactUseCase registerPatientContactUseCase(PatientContactRepository r) { return new RegisterPatientContactUseCase(r); }
    @Bean public GetPatientContactByIdUseCase getPatientContactByIdUseCase(PatientContactRepository r) { return new GetPatientContactByIdUseCase(r); }
    @Bean public ListPatientContactUseCase listPatientContactUseCase(PatientContactRepository r) { return new ListPatientContactUseCase(r); }
    @Bean public UpdatePatientContactUseCase updatePatientContactUseCase(PatientContactRepository r) { return new UpdatePatientContactUseCase(r); }
    @Bean public DeletePatientContactUseCase deletePatientContactUseCase(PatientContactRepository r) { return new DeletePatientContactUseCase(r); }
}
