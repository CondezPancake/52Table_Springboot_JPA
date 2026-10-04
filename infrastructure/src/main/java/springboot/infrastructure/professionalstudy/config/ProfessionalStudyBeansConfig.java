package springboot.infrastructure.professionalstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.professionalstudy.usecase.*;
import springboot.domain.professionalstudy.port.repository.ProfessionalStudyRepository;
import springboot.infrastructure.professionalstudy.adapters.out.persistence.mappers.ProfessionalStudyPersistenceMapper;
import springboot.infrastructure.professionalstudy.adapters.out.persistence.repositories.ProfessionalStudyJpaRepository;
import springboot.infrastructure.professionalstudy.adapters.out.persistence.repositories.ProfessionalStudyRepositoryAdapter;

@Configuration
public class ProfessionalStudyBeansConfig {
    @Bean public ProfessionalStudyPersistenceMapper professionalstudyPersistenceMapper() { return new ProfessionalStudyPersistenceMapper(); }
    @Bean public ProfessionalStudyRepository professionalstudyRepository(ProfessionalStudyJpaRepository repository, ProfessionalStudyPersistenceMapper mapper) {
        return new ProfessionalStudyRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterProfessionalStudyUseCase registerProfessionalStudyUseCase(ProfessionalStudyRepository r) { return new RegisterProfessionalStudyUseCase(r); }
    @Bean public GetProfessionalStudyByIdUseCase getProfessionalStudyByIdUseCase(ProfessionalStudyRepository r) { return new GetProfessionalStudyByIdUseCase(r); }
    @Bean public ListProfessionalStudyUseCase listProfessionalStudyUseCase(ProfessionalStudyRepository r) { return new ListProfessionalStudyUseCase(r); }
    @Bean public UpdateProfessionalStudyUseCase updateProfessionalStudyUseCase(ProfessionalStudyRepository r) { return new UpdateProfessionalStudyUseCase(r); }
    @Bean public DeleteProfessionalStudyUseCase deleteProfessionalStudyUseCase(ProfessionalStudyRepository r) { return new DeleteProfessionalStudyUseCase(r); }
}
