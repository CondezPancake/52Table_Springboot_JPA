package springboot.infrastructure.chatescalationstatushistory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.chatescalationstatushistory.usecase.*;
import springboot.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import springboot.infrastructure.chatescalationstatushistory.adapters.out.persistence.mappers.ChatEscalationStatusHistoryPersistenceMapper;
import springboot.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories.ChatEscalationStatusHistoryJpaRepository;
import springboot.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories.ChatEscalationStatusHistoryRepositoryAdapter;

@Configuration
public class ChatEscalationStatusHistoryBeansConfig {
    @Bean public ChatEscalationStatusHistoryPersistenceMapper chatescalationstatushistoryPersistenceMapper() { return new ChatEscalationStatusHistoryPersistenceMapper(); }
    @Bean public ChatEscalationStatusHistoryRepository chatescalationstatushistoryRepository(ChatEscalationStatusHistoryJpaRepository repository, ChatEscalationStatusHistoryPersistenceMapper mapper) {
        return new ChatEscalationStatusHistoryRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterChatEscalationStatusHistoryUseCase registerChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository r) { return new RegisterChatEscalationStatusHistoryUseCase(r); }
    @Bean public GetChatEscalationStatusHistoryByIdUseCase getChatEscalationStatusHistoryByIdUseCase(ChatEscalationStatusHistoryRepository r) { return new GetChatEscalationStatusHistoryByIdUseCase(r); }
    @Bean public ListChatEscalationStatusHistoryUseCase listChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository r) { return new ListChatEscalationStatusHistoryUseCase(r); }
    @Bean public UpdateChatEscalationStatusHistoryUseCase updateChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository r) { return new UpdateChatEscalationStatusHistoryUseCase(r); }
    @Bean public DeleteChatEscalationStatusHistoryUseCase deleteChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository r) { return new DeleteChatEscalationStatusHistoryUseCase(r); }
}
