package springboot.infrastructure.professional.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.professional.usecase.*;
import springboot.domain.professional.port.repository.ProfessionalRepository;
import springboot.infrastructure.professional.adapters.out.persistence.mappers.ProfessionalPersistenceMapper;
import springboot.infrastructure.professional.adapters.out.persistence.repositories.ProfessionalJpaRepository;
import springboot.infrastructure.professional.adapters.out.persistence.repositories.ProfessionalRepositoryAdapter;

@Configuration
public class ProfessionalBeansConfig {
    @Bean public ProfessionalPersistenceMapper professionalPersistenceMapper() { return new ProfessionalPersistenceMapper(); }
    @Bean public ProfessionalRepository professionalRepository(ProfessionalJpaRepository repository, ProfessionalPersistenceMapper mapper) {
        return new ProfessionalRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterProfessionalUseCase registerProfessionalUseCase(ProfessionalRepository r) { return new RegisterProfessionalUseCase(r); }
    @Bean public GetProfessionalByIdUseCase getProfessionalByIdUseCase(ProfessionalRepository r) { return new GetProfessionalByIdUseCase(r); }
    @Bean public ListProfessionalUseCase listProfessionalUseCase(ProfessionalRepository r) { return new ListProfessionalUseCase(r); }
    @Bean public UpdateProfessionalUseCase updateProfessionalUseCase(ProfessionalRepository r) { return new UpdateProfessionalUseCase(r); }
    @Bean public DeleteProfessionalUseCase deleteProfessionalUseCase(ProfessionalRepository r) { return new DeleteProfessionalUseCase(r); }
}
