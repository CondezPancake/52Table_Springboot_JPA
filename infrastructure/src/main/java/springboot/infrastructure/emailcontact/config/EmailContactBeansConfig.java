package springboot.infrastructure.emailcontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.emailcontact.usecase.*;
import springboot.domain.emailcontact.port.repository.EmailContactRepository;
import springboot.infrastructure.emailcontact.adapters.out.persistence.mappers.EmailContactPersistenceMapper;
import springboot.infrastructure.emailcontact.adapters.out.persistence.repositories.EmailContactJpaRepository;
import springboot.infrastructure.emailcontact.adapters.out.persistence.repositories.EmailContactRepositoryAdapter;

@Configuration
public class EmailContactBeansConfig {
    @Bean public EmailContactPersistenceMapper emailcontactPersistenceMapper() { return new EmailContactPersistenceMapper(); }
    @Bean public EmailContactRepository emailcontactRepository(EmailContactJpaRepository repository, EmailContactPersistenceMapper mapper) {
        return new EmailContactRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterEmailContactUseCase registerEmailContactUseCase(EmailContactRepository r) { return new RegisterEmailContactUseCase(r); }
    @Bean public GetEmailContactByIdUseCase getEmailContactByIdUseCase(EmailContactRepository r) { return new GetEmailContactByIdUseCase(r); }
    @Bean public ListEmailContactUseCase listEmailContactUseCase(EmailContactRepository r) { return new ListEmailContactUseCase(r); }
    @Bean public UpdateEmailContactUseCase updateEmailContactUseCase(EmailContactRepository r) { return new UpdateEmailContactUseCase(r); }
    @Bean public DeleteEmailContactUseCase deleteEmailContactUseCase(EmailContactRepository r) { return new DeleteEmailContactUseCase(r); }
}
