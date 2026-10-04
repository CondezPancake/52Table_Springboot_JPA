package springboot.infrastructure.chatescalation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.chatescalation.usecase.*;
import springboot.domain.chatescalation.port.repository.ChatEscalationRepository;
import springboot.infrastructure.chatescalation.adapters.out.persistence.mappers.ChatEscalationPersistenceMapper;
import springboot.infrastructure.chatescalation.adapters.out.persistence.repositories.ChatEscalationJpaRepository;
import springboot.infrastructure.chatescalation.adapters.out.persistence.repositories.ChatEscalationRepositoryAdapter;

@Configuration
public class ChatEscalationBeansConfig {
    @Bean public ChatEscalationPersistenceMapper chatescalationPersistenceMapper() { return new ChatEscalationPersistenceMapper(); }
    @Bean public ChatEscalationRepository chatescalationRepository(ChatEscalationJpaRepository repository, ChatEscalationPersistenceMapper mapper) {
        return new ChatEscalationRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterChatEscalationUseCase registerChatEscalationUseCase(ChatEscalationRepository r) { return new RegisterChatEscalationUseCase(r); }
    @Bean public GetChatEscalationByIdUseCase getChatEscalationByIdUseCase(ChatEscalationRepository r) { return new GetChatEscalationByIdUseCase(r); }
    @Bean public ListChatEscalationUseCase listChatEscalationUseCase(ChatEscalationRepository r) { return new ListChatEscalationUseCase(r); }
    @Bean public UpdateChatEscalationUseCase updateChatEscalationUseCase(ChatEscalationRepository r) { return new UpdateChatEscalationUseCase(r); }
    @Bean public DeleteChatEscalationUseCase deleteChatEscalationUseCase(ChatEscalationRepository r) { return new DeleteChatEscalationUseCase(r); }
}
