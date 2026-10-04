package springboot.infrastructure.chatconversationaisetting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.chatconversationaisetting.usecase.*;
import springboot.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;
import springboot.infrastructure.chatconversationaisetting.adapters.out.persistence.mappers.ChatConversationAiSettingPersistenceMapper;
import springboot.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories.ChatConversationAiSettingJpaRepository;
import springboot.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories.ChatConversationAiSettingRepositoryAdapter;

@Configuration
public class ChatConversationAiSettingBeansConfig {
    @Bean public ChatConversationAiSettingPersistenceMapper chatconversationaisettingPersistenceMapper() { return new ChatConversationAiSettingPersistenceMapper(); }
    @Bean public ChatConversationAiSettingRepository chatconversationaisettingRepository(ChatConversationAiSettingJpaRepository repository, ChatConversationAiSettingPersistenceMapper mapper) {
        return new ChatConversationAiSettingRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterChatConversationAiSettingUseCase registerChatConversationAiSettingUseCase(ChatConversationAiSettingRepository r) { return new RegisterChatConversationAiSettingUseCase(r); }
    @Bean public GetChatConversationAiSettingByIdUseCase getChatConversationAiSettingByIdUseCase(ChatConversationAiSettingRepository r) { return new GetChatConversationAiSettingByIdUseCase(r); }
    @Bean public ListChatConversationAiSettingUseCase listChatConversationAiSettingUseCase(ChatConversationAiSettingRepository r) { return new ListChatConversationAiSettingUseCase(r); }
    @Bean public UpdateChatConversationAiSettingUseCase updateChatConversationAiSettingUseCase(ChatConversationAiSettingRepository r) { return new UpdateChatConversationAiSettingUseCase(r); }
    @Bean public DeleteChatConversationAiSettingUseCase deleteChatConversationAiSettingUseCase(ChatConversationAiSettingRepository r) { return new DeleteChatConversationAiSettingUseCase(r); }
}
