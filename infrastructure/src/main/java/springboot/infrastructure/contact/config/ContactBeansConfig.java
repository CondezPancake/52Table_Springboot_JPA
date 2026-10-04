package springboot.infrastructure.contact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.contact.usecase.*;
import springboot.domain.contact.port.repository.ContactRepository;
import springboot.infrastructure.contact.adapters.out.persistence.mappers.ContactPersistenceMapper;
import springboot.infrastructure.contact.adapters.out.persistence.repositories.ContactJpaRepository;
import springboot.infrastructure.contact.adapters.out.persistence.repositories.ContactRepositoryAdapter;

@Configuration
public class ContactBeansConfig {
    @Bean public ContactPersistenceMapper contactPersistenceMapper() { return new ContactPersistenceMapper(); }
    @Bean public ContactRepository contactRepository(ContactJpaRepository repository, ContactPersistenceMapper mapper) {
        return new ContactRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterContactUseCase registerContactUseCase(ContactRepository r) { return new RegisterContactUseCase(r); }
    @Bean public GetContactByIdUseCase getContactByIdUseCase(ContactRepository r) { return new GetContactByIdUseCase(r); }
    @Bean public ListContactUseCase listContactUseCase(ContactRepository r) { return new ListContactUseCase(r); }
    @Bean public UpdateContactUseCase updateContactUseCase(ContactRepository r) { return new UpdateContactUseCase(r); }
    @Bean public DeleteContactUseCase deleteContactUseCase(ContactRepository r) { return new DeleteContactUseCase(r); }
}
