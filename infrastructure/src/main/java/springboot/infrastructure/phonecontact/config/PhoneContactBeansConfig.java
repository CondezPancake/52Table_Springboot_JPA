package springboot.infrastructure.phonecontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.phonecontact.usecase.*;
import springboot.domain.phonecontact.port.repository.PhoneContactRepository;
import springboot.infrastructure.phonecontact.adapters.out.persistence.mappers.PhoneContactPersistenceMapper;
import springboot.infrastructure.phonecontact.adapters.out.persistence.repositories.PhoneContactJpaRepository;
import springboot.infrastructure.phonecontact.adapters.out.persistence.repositories.PhoneContactRepositoryAdapter;

@Configuration
public class PhoneContactBeansConfig {
    @Bean public PhoneContactPersistenceMapper phonecontactPersistenceMapper() { return new PhoneContactPersistenceMapper(); }
    @Bean public PhoneContactRepository phonecontactRepository(PhoneContactJpaRepository repository, PhoneContactPersistenceMapper mapper) {
        return new PhoneContactRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterPhoneContactUseCase registerPhoneContactUseCase(PhoneContactRepository r) { return new RegisterPhoneContactUseCase(r); }
    @Bean public GetPhoneContactByIdUseCase getPhoneContactByIdUseCase(PhoneContactRepository r) { return new GetPhoneContactByIdUseCase(r); }
    @Bean public ListPhoneContactUseCase listPhoneContactUseCase(PhoneContactRepository r) { return new ListPhoneContactUseCase(r); }
    @Bean public UpdatePhoneContactUseCase updatePhoneContactUseCase(PhoneContactRepository r) { return new UpdatePhoneContactUseCase(r); }
    @Bean public DeletePhoneContactUseCase deletePhoneContactUseCase(PhoneContactRepository r) { return new DeletePhoneContactUseCase(r); }
}
