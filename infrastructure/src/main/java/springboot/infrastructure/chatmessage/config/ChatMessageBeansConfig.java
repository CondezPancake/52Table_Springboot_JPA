package springboot.infrastructure.chatmessage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.chatmessage.usecase.*;
import springboot.domain.chatmessage.port.repository.ChatMessageRepository;
import springboot.infrastructure.chatmessage.adapters.out.persistence.mappers.ChatMessagePersistenceMapper;
import springboot.infrastructure.chatmessage.adapters.out.persistence.repositories.ChatMessageJpaRepository;
import springboot.infrastructure.chatmessage.adapters.out.persistence.repositories.ChatMessageRepositoryAdapter;

@Configuration
public class ChatMessageBeansConfig {
    @Bean public ChatMessagePersistenceMapper chatmessagePersistenceMapper() { return new ChatMessagePersistenceMapper(); }
    @Bean public ChatMessageRepository chatmessageRepository(ChatMessageJpaRepository repository, ChatMessagePersistenceMapper mapper) {
        return new ChatMessageRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterChatMessageUseCase registerChatMessageUseCase(ChatMessageRepository r) { return new RegisterChatMessageUseCase(r); }
    @Bean public GetChatMessageByIdUseCase getChatMessageByIdUseCase(ChatMessageRepository r) { return new GetChatMessageByIdUseCase(r); }
    @Bean public ListChatMessageUseCase listChatMessageUseCase(ChatMessageRepository r) { return new ListChatMessageUseCase(r); }
    @Bean public UpdateChatMessageUseCase updateChatMessageUseCase(ChatMessageRepository r) { return new UpdateChatMessageUseCase(r); }
    @Bean public DeleteChatMessageUseCase deleteChatMessageUseCase(ChatMessageRepository r) { return new DeleteChatMessageUseCase(r); }
}
