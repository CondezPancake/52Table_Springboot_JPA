package springboot.infrastructure.conversationstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.conversationstatus.usecase.*;
import springboot.domain.conversationstatus.port.repository.ConversationStatusRepository;
import springboot.infrastructure.conversationstatus.adapters.out.persistence.mappers.ConversationStatusPersistenceMapper;
import springboot.infrastructure.conversationstatus.adapters.out.persistence.repositories.ConversationStatusJpaRepository;
import springboot.infrastructure.conversationstatus.adapters.out.persistence.repositories.ConversationStatusRepositoryAdapter;

@Configuration
public class ConversationStatusBeansConfig {
    @Bean public ConversationStatusPersistenceMapper conversationstatusPersistenceMapper() { return new ConversationStatusPersistenceMapper(); }
    @Bean public ConversationStatusRepository conversationstatusRepository(ConversationStatusJpaRepository repository, ConversationStatusPersistenceMapper mapper) {
        return new ConversationStatusRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterConversationStatusUseCase registerConversationStatusUseCase(ConversationStatusRepository r) { return new RegisterConversationStatusUseCase(r); }
    @Bean public GetConversationStatusByIdUseCase getConversationStatusByIdUseCase(ConversationStatusRepository r) { return new GetConversationStatusByIdUseCase(r); }
    @Bean public ListConversationStatusUseCase listConversationStatusUseCase(ConversationStatusRepository r) { return new ListConversationStatusUseCase(r); }
    @Bean public UpdateConversationStatusUseCase updateConversationStatusUseCase(ConversationStatusRepository r) { return new UpdateConversationStatusUseCase(r); }
    @Bean public DeleteConversationStatusUseCase deleteConversationStatusUseCase(ConversationStatusRepository r) { return new DeleteConversationStatusUseCase(r); }
}
