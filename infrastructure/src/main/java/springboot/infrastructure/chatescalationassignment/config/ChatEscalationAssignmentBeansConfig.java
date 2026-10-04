package springboot.infrastructure.chatescalationassignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.application.chatescalationassignment.usecase.*;
import springboot.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import springboot.infrastructure.chatescalationassignment.adapters.out.persistence.mappers.ChatEscalationAssignmentPersistenceMapper;
import springboot.infrastructure.chatescalationassignment.adapters.out.persistence.repositories.ChatEscalationAssignmentJpaRepository;
import springboot.infrastructure.chatescalationassignment.adapters.out.persistence.repositories.ChatEscalationAssignmentRepositoryAdapter;

@Configuration
public class ChatEscalationAssignmentBeansConfig {
    @Bean public ChatEscalationAssignmentPersistenceMapper chatescalationassignmentPersistenceMapper() { return new ChatEscalationAssignmentPersistenceMapper(); }
    @Bean public ChatEscalationAssignmentRepository chatescalationassignmentRepository(ChatEscalationAssignmentJpaRepository repository, ChatEscalationAssignmentPersistenceMapper mapper) {
        return new ChatEscalationAssignmentRepositoryAdapter(repository, mapper);
    }
    @Bean public RegisterChatEscalationAssignmentUseCase registerChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository r) { return new RegisterChatEscalationAssignmentUseCase(r); }
    @Bean public GetChatEscalationAssignmentByIdUseCase getChatEscalationAssignmentByIdUseCase(ChatEscalationAssignmentRepository r) { return new GetChatEscalationAssignmentByIdUseCase(r); }
    @Bean public ListChatEscalationAssignmentUseCase listChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository r) { return new ListChatEscalationAssignmentUseCase(r); }
    @Bean public UpdateChatEscalationAssignmentUseCase updateChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository r) { return new UpdateChatEscalationAssignmentUseCase(r); }
    @Bean public DeleteChatEscalationAssignmentUseCase deleteChatEscalationAssignmentUseCase(ChatEscalationAssignmentRepository r) { return new DeleteChatEscalationAssignmentUseCase(r); }
}
