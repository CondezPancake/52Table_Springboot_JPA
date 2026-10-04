package springboot.infrastructure.consenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.consenttype.usecase.*;
import springboot.domain.consenttype.port.repository.ConsentTypeRepository;
import springboot.infrastructure.consenttype.adapters.out.persistence.mappers.ConsentTypePersistenceMapper;
import springboot.infrastructure.consenttype.adapters.out.persistence.repositories.ConsentTypeJpaRepository;
import springboot.infrastructure.consenttype.adapters.out.persistence.repositories.ConsentTypeRepositoryAdapter;

@Configuration
public class ConsentTypeBeansConfig {
    @Bean public ConsentTypePersistenceMapper consenttypePersistenceMapper() { return new ConsentTypePersistenceMapper(); }
    @Bean public ConsentTypeRepository consenttypeRepository(ConsentTypeJpaRepository repository, ConsentTypePersistenceMapper mapper) {
        return new ConsentTypeRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterConsentTypeUseCase registerConsentTypeUseCase(ConsentTypeRepository r) { return new RegisterConsentTypeUseCase(r); }
    @Bean public GetConsentTypeByIdUseCase getConsentTypeByIdUseCase(ConsentTypeRepository r) { return new GetConsentTypeByIdUseCase(r); }
    @Bean public ListConsentTypeUseCase listConsentTypeUseCase(ConsentTypeRepository r) { return new ListConsentTypeUseCase(r); }
    @Bean public UpdateConsentTypeUseCase updateConsentTypeUseCase(ConsentTypeRepository r) { return new UpdateConsentTypeUseCase(r); }
    @Bean public DeleteConsentTypeUseCase deleteConsentTypeUseCase(ConsentTypeRepository r) { return new DeleteConsentTypeUseCase(r); }
}
