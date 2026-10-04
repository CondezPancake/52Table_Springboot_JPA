package springboot.application.chatescalation.usecase;

import springboot.application.chatescalation.command.RegisterChatEscalationCommand;
import springboot.application.chatescalation.dto.ChatEscalationResponse;
import springboot.domain.chatescalation.model.aggregate.ChatEscalation;
import springboot.domain.chatescalation.port.repository.ChatEscalationRepository;

public class RegisterChatEscalationUseCase {
    private final ChatEscalationRepository repository;
    public RegisterChatEscalationUseCase(ChatEscalationRepository repository) { this.repository = repository; }

    public ChatEscalationResponse execute(RegisterChatEscalationCommand command) {
        ChatEscalation aggregate = ChatEscalation.register(
                command.conversationId(),
                command.statusId(),
                command.fromAi(),
                command.reason());
        ChatEscalation saved = repository.save(aggregate);
        return new ChatEscalationResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.statusId().value(),
                saved.fromAi(),
                saved.reason(),
                saved.createdAt());
    }
}
