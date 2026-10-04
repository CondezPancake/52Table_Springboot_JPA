package springboot.infrastructure.chatairun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.chatairun.usecase.*;
import springboot.domain.chatairun.port.repository.ChatAiRunRepository;
import springboot.infrastructure.chatairun.adapters.out.persistence.mappers.ChatAiRunPersistenceMapper;
import springboot.infrastructure.chatairun.adapters.out.persistence.repositories.ChatAiRunJpaRepository;
import springboot.infrastructure.chatairun.adapters.out.persistence.repositories.ChatAiRunRepositoryAdapter;

@Configuration
public class ChatAiRunBeansConfig {
    @Bean public ChatAiRunPersistenceMapper chatairunPersistenceMapper() { return new ChatAiRunPersistenceMapper(); }
    @Bean public ChatAiRunRepository chatairunRepository(ChatAiRunJpaRepository repository, ChatAiRunPersistenceMapper mapper) {
        return new ChatAiRunRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterChatAiRunUseCase registerChatAiRunUseCase(ChatAiRunRepository r) { return new RegisterChatAiRunUseCase(r); }
    @Bean public GetChatAiRunByIdUseCase getChatAiRunByIdUseCase(ChatAiRunRepository r) { return new GetChatAiRunByIdUseCase(r); }
    @Bean public ListChatAiRunUseCase listChatAiRunUseCase(ChatAiRunRepository r) { return new ListChatAiRunUseCase(r); }
    @Bean public UpdateChatAiRunUseCase updateChatAiRunUseCase(ChatAiRunRepository r) { return new UpdateChatAiRunUseCase(r); }
    @Bean public DeleteChatAiRunUseCase deleteChatAiRunUseCase(ChatAiRunRepository r) { return new DeleteChatAiRunUseCase(r); }
}
