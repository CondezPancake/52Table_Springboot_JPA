package springboot.infrastructure.messagetype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.messagetype.usecase.*;
import springboot.domain.messagetype.port.repository.MessageTypeRepository;
import springboot.infrastructure.messagetype.adapters.out.persistence.mappers.MessageTypePersistenceMapper;
import springboot.infrastructure.messagetype.adapters.out.persistence.repositories.MessageTypeJpaRepository;
import springboot.infrastructure.messagetype.adapters.out.persistence.repositories.MessageTypeRepositoryAdapter;

@Configuration
public class MessageTypeBeansConfig {
    @Bean public MessageTypePersistenceMapper messagetypePersistenceMapper() { return new MessageTypePersistenceMapper(); }
    @Bean public MessageTypeRepository messagetypeRepository(MessageTypeJpaRepository repository, MessageTypePersistenceMapper mapper) {
        return new MessageTypeRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterMessageTypeUseCase registerMessageTypeUseCase(MessageTypeRepository r) { return new RegisterMessageTypeUseCase(r); }
    @Bean public GetMessageTypeByIdUseCase getMessageTypeByIdUseCase(MessageTypeRepository r) { return new GetMessageTypeByIdUseCase(r); }
    @Bean public ListMessageTypeUseCase listMessageTypeUseCase(MessageTypeRepository r) { return new ListMessageTypeUseCase(r); }
    @Bean public UpdateMessageTypeUseCase updateMessageTypeUseCase(MessageTypeRepository r) { return new UpdateMessageTypeUseCase(r); }
    @Bean public DeleteMessageTypeUseCase deleteMessageTypeUseCase(MessageTypeRepository r) { return new DeleteMessageTypeUseCase(r); }
}
