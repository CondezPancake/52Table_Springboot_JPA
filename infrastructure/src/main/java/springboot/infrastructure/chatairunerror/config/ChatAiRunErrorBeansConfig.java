package springboot.infrastructure.chatairunerror.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.chatairunerror.usecase.*;
import springboot.domain.chatairunerror.port.repository.ChatAiRunErrorRepository;
import springboot.infrastructure.chatairunerror.adapters.out.persistence.mappers.ChatAiRunErrorPersistenceMapper;
import springboot.infrastructure.chatairunerror.adapters.out.persistence.repositories.ChatAiRunErrorJpaRepository;
import springboot.infrastructure.chatairunerror.adapters.out.persistence.repositories.ChatAiRunErrorRepositoryAdapter;

@Configuration
public class ChatAiRunErrorBeansConfig {
    @Bean public ChatAiRunErrorPersistenceMapper chatairunerrorPersistenceMapper() { return new ChatAiRunErrorPersistenceMapper(); }
    @Bean public ChatAiRunErrorRepository chatairunerrorRepository(ChatAiRunErrorJpaRepository repository, ChatAiRunErrorPersistenceMapper mapper) {
        return new ChatAiRunErrorRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterChatAiRunErrorUseCase registerChatAiRunErrorUseCase(ChatAiRunErrorRepository r) { return new RegisterChatAiRunErrorUseCase(r); }
    @Bean public GetChatAiRunErrorByIdUseCase getChatAiRunErrorByIdUseCase(ChatAiRunErrorRepository r) { return new GetChatAiRunErrorByIdUseCase(r); }
    @Bean public ListChatAiRunErrorUseCase listChatAiRunErrorUseCase(ChatAiRunErrorRepository r) { return new ListChatAiRunErrorUseCase(r); }
    @Bean public UpdateChatAiRunErrorUseCase updateChatAiRunErrorUseCase(ChatAiRunErrorRepository r) { return new UpdateChatAiRunErrorUseCase(r); }
    @Bean public DeleteChatAiRunErrorUseCase deleteChatAiRunErrorUseCase(ChatAiRunErrorRepository r) { return new DeleteChatAiRunErrorUseCase(r); }
}
