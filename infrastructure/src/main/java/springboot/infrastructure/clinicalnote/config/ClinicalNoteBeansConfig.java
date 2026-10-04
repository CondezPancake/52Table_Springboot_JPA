package springboot.infrastructure.clinicalnote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.clinicalnote.usecase.*;
import springboot.domain.clinicalnote.port.repository.ClinicalNoteRepository;
import springboot.infrastructure.clinicalnote.adapters.out.persistence.mappers.ClinicalNotePersistenceMapper;
import springboot.infrastructure.clinicalnote.adapters.out.persistence.repositories.ClinicalNoteJpaRepository;
import springboot.infrastructure.clinicalnote.adapters.out.persistence.repositories.ClinicalNoteRepositoryAdapter;

@Configuration
public class ClinicalNoteBeansConfig {
    @Bean public ClinicalNotePersistenceMapper clinicalnotePersistenceMapper() { return new ClinicalNotePersistenceMapper(); }
    @Bean public ClinicalNoteRepository clinicalnoteRepository(ClinicalNoteJpaRepository repository, ClinicalNotePersistenceMapper mapper) {
        return new ClinicalNoteRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterClinicalNoteUseCase registerClinicalNoteUseCase(ClinicalNoteRepository r) { return new RegisterClinicalNoteUseCase(r); }
    @Bean public GetClinicalNoteByIdUseCase getClinicalNoteByIdUseCase(ClinicalNoteRepository r) { return new GetClinicalNoteByIdUseCase(r); }
    @Bean public ListClinicalNoteUseCase listClinicalNoteUseCase(ClinicalNoteRepository r) { return new ListClinicalNoteUseCase(r); }
    @Bean public UpdateClinicalNoteUseCase updateClinicalNoteUseCase(ClinicalNoteRepository r) { return new UpdateClinicalNoteUseCase(r); }
    @Bean public DeleteClinicalNoteUseCase deleteClinicalNoteUseCase(ClinicalNoteRepository r) { return new DeleteClinicalNoteUseCase(r); }
}
