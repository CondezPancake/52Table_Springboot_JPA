package springboot.infrastructure.chatairunmetric.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.chatairunmetric.usecase.*;
import springboot.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;
import springboot.infrastructure.chatairunmetric.adapters.out.persistence.mappers.ChatAiRunMetricPersistenceMapper;
import springboot.infrastructure.chatairunmetric.adapters.out.persistence.repositories.ChatAiRunMetricJpaRepository;
import springboot.infrastructure.chatairunmetric.adapters.out.persistence.repositories.ChatAiRunMetricRepositoryAdapter;

@Configuration
public class ChatAiRunMetricBeansConfig {
    @Bean public ChatAiRunMetricPersistenceMapper chatairunmetricPersistenceMapper() { return new ChatAiRunMetricPersistenceMapper(); }
    @Bean public ChatAiRunMetricRepository chatairunmetricRepository(ChatAiRunMetricJpaRepository repository, ChatAiRunMetricPersistenceMapper mapper) {
        return new ChatAiRunMetricRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterChatAiRunMetricUseCase registerChatAiRunMetricUseCase(ChatAiRunMetricRepository r) { return new RegisterChatAiRunMetricUseCase(r); }
    @Bean public GetChatAiRunMetricByIdUseCase getChatAiRunMetricByIdUseCase(ChatAiRunMetricRepository r) { return new GetChatAiRunMetricByIdUseCase(r); }
    @Bean public ListChatAiRunMetricUseCase listChatAiRunMetricUseCase(ChatAiRunMetricRepository r) { return new ListChatAiRunMetricUseCase(r); }
    @Bean public UpdateChatAiRunMetricUseCase updateChatAiRunMetricUseCase(ChatAiRunMetricRepository r) { return new UpdateChatAiRunMetricUseCase(r); }
    @Bean public DeleteChatAiRunMetricUseCase deleteChatAiRunMetricUseCase(ChatAiRunMetricRepository r) { return new DeleteChatAiRunMetricUseCase(r); }
}
