package springboot.infrastructure.patientallergy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.patientallergy.usecase.*;
import springboot.domain.patientallergy.port.repository.PatientAllergyRepository;
import springboot.infrastructure.patientallergy.adapters.out.persistence.mappers.PatientAllergyPersistenceMapper;
import springboot.infrastructure.patientallergy.adapters.out.persistence.repositories.PatientAllergyJpaRepository;
import springboot.infrastructure.patientallergy.adapters.out.persistence.repositories.PatientAllergyRepositoryAdapter;

@Configuration
public class PatientAllergyBeansConfig {
    @Bean public PatientAllergyPersistenceMapper patientallergyPersistenceMapper() { return new PatientAllergyPersistenceMapper(); }
    @Bean public PatientAllergyRepository patientallergyRepository(PatientAllergyJpaRepository repository, PatientAllergyPersistenceMapper mapper) {
        return new PatientAllergyRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterPatientAllergyUseCase registerPatientAllergyUseCase(PatientAllergyRepository r) { return new RegisterPatientAllergyUseCase(r); }
    @Bean public GetPatientAllergyByIdUseCase getPatientAllergyByIdUseCase(PatientAllergyRepository r) { return new GetPatientAllergyByIdUseCase(r); }
    @Bean public ListPatientAllergyUseCase listPatientAllergyUseCase(PatientAllergyRepository r) { return new ListPatientAllergyUseCase(r); }
    @Bean public UpdatePatientAllergyUseCase updatePatientAllergyUseCase(PatientAllergyRepository r) { return new UpdatePatientAllergyUseCase(r); }
    @Bean public DeletePatientAllergyUseCase deletePatientAllergyUseCase(PatientAllergyRepository r) { return new DeletePatientAllergyUseCase(r); }
}
