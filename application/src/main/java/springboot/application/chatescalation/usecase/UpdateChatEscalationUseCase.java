package springboot.application.chatescalation.usecase;

import springboot.application.chatescalation.command.UpdateChatEscalationCommand;
import springboot.application.chatescalation.dto.ChatEscalationResponse;
import springboot.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import springboot.domain.chatescalation.port.repository.ChatEscalationRepository;

public class UpdateChatEscalationUseCase {
    private final ChatEscalationRepository repository;
    public UpdateChatEscalationUseCase(ChatEscalationRepository repository) { this.repository = repository; }

    public ChatEscalationResponse execute(UpdateChatEscalationCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ChatEscalationNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.conversationId(),
                command.statusId(),
                command.fromAi(),
                command.reason());
        var saved = repository.save(aggregate);
        return new ChatEscalationResponse(
                saved.id().value(),
                saved.conversationId().value(),
                saved.statusId().value(),
                saved.fromAi(),
                saved.reason(),
                saved.createdAt());
    }
}
