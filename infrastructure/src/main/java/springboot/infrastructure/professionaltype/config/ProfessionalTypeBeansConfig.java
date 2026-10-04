package springboot.infrastructure.professionaltype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.professionaltype.usecase.*;
import springboot.domain.professionaltype.port.repository.ProfessionalTypeRepository;
import springboot.infrastructure.professionaltype.adapters.out.persistence.mappers.ProfessionalTypePersistenceMapper;
import springboot.infrastructure.professionaltype.adapters.out.persistence.repositories.ProfessionalTypeJpaRepository;
import springboot.infrastructure.professionaltype.adapters.out.persistence.repositories.ProfessionalTypeRepositoryAdapter;

@Configuration
public class ProfessionalTypeBeansConfig {
    @Bean public ProfessionalTypePersistenceMapper professionaltypePersistenceMapper() { return new ProfessionalTypePersistenceMapper(); }
    @Bean public ProfessionalTypeRepository professionaltypeRepository(ProfessionalTypeJpaRepository repository, ProfessionalTypePersistenceMapper mapper) {
        return new ProfessionalTypeRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterProfessionalTypeUseCase registerProfessionalTypeUseCase(ProfessionalTypeRepository r) { return new RegisterProfessionalTypeUseCase(r); }
    @Bean public GetProfessionalTypeByIdUseCase getProfessionalTypeByIdUseCase(ProfessionalTypeRepository r) { return new GetProfessionalTypeByIdUseCase(r); }
    @Bean public ListProfessionalTypeUseCase listProfessionalTypeUseCase(ProfessionalTypeRepository r) { return new ListProfessionalTypeUseCase(r); }
    @Bean public UpdateProfessionalTypeUseCase updateProfessionalTypeUseCase(ProfessionalTypeRepository r) { return new UpdateProfessionalTypeUseCase(r); }
    @Bean public DeleteProfessionalTypeUseCase deleteProfessionalTypeUseCase(ProfessionalTypeRepository r) { return new DeleteProfessionalTypeUseCase(r); }
}
