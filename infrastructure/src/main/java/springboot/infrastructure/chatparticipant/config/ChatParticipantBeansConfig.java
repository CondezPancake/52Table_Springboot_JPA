package springboot.infrastructure.chatparticipant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.chatparticipant.usecase.*;
import springboot.domain.chatparticipant.port.repository.ChatParticipantRepository;
import springboot.infrastructure.chatparticipant.adapters.out.persistence.mappers.ChatParticipantPersistenceMapper;
import springboot.infrastructure.chatparticipant.adapters.out.persistence.repositories.ChatParticipantJpaRepository;
import springboot.infrastructure.chatparticipant.adapters.out.persistence.repositories.ChatParticipantRepositoryAdapter;

@Configuration
public class ChatParticipantBeansConfig {
    @Bean public ChatParticipantPersistenceMapper chatparticipantPersistenceMapper() { return new ChatParticipantPersistenceMapper(); }
    @Bean public ChatParticipantRepository chatparticipantRepository(ChatParticipantJpaRepository repository, ChatParticipantPersistenceMapper mapper) {
        return new ChatParticipantRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterChatParticipantUseCase registerChatParticipantUseCase(ChatParticipantRepository r) { return new RegisterChatParticipantUseCase(r); }
    @Bean public GetChatParticipantByIdUseCase getChatParticipantByIdUseCase(ChatParticipantRepository r) { return new GetChatParticipantByIdUseCase(r); }
    @Bean public ListChatParticipantUseCase listChatParticipantUseCase(ChatParticipantRepository r) { return new ListChatParticipantUseCase(r); }
    @Bean public UpdateChatParticipantUseCase updateChatParticipantUseCase(ChatParticipantRepository r) { return new UpdateChatParticipantUseCase(r); }
    @Bean public DeleteChatParticipantUseCase deleteChatParticipantUseCase(ChatParticipantRepository r) { return new DeleteChatParticipantUseCase(r); }
}
