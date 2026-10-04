package springboot.infrastructure.chatconversation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.chatconversation.usecase.*;
import springboot.domain.chatconversation.port.repository.ChatConversationRepository;
import springboot.infrastructure.chatconversation.adapters.out.persistence.mappers.ChatConversationPersistenceMapper;
import springboot.infrastructure.chatconversation.adapters.out.persistence.repositories.ChatConversationJpaRepository;
import springboot.infrastructure.chatconversation.adapters.out.persistence.repositories.ChatConversationRepositoryAdapter;

@Configuration
public class ChatConversationBeansConfig {
    @Bean public ChatConversationPersistenceMapper chatconversationPersistenceMapper() { return new ChatConversationPersistenceMapper(); }
    @Bean public ChatConversationRepository chatconversationRepository(ChatConversationJpaRepository repository, ChatConversationPersistenceMapper mapper) {
        return new ChatConversationRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterChatConversationUseCase registerChatConversationUseCase(ChatConversationRepository r) { return new RegisterChatConversationUseCase(r); }
    @Bean public GetChatConversationByIdUseCase getChatConversationByIdUseCase(ChatConversationRepository r) { return new GetChatConversationByIdUseCase(r); }
    @Bean public ListChatConversationUseCase listChatConversationUseCase(ChatConversationRepository r) { return new ListChatConversationUseCase(r); }
    @Bean public UpdateChatConversationUseCase updateChatConversationUseCase(ChatConversationRepository r) { return new UpdateChatConversationUseCase(r); }
    @Bean public DeleteChatConversationUseCase deleteChatConversationUseCase(ChatConversationRepository r) { return new DeleteChatConversationUseCase(r); }
}
