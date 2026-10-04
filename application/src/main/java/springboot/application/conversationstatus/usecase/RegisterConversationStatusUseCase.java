package springboot.application.conversationstatus.usecase;

import springboot.application.conversationstatus.command.RegisterConversationStatusCommand;
import springboot.application.conversationstatus.dto.ConversationStatusResponse;
import springboot.domain.conversationstatus.model.aggregate.ConversationStatus;
import springboot.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class RegisterConversationStatusUseCase {
    private final ConversationStatusRepository repository;
    public RegisterConversationStatusUseCase(ConversationStatusRepository repository) { this.repository = repository; }

    public ConversationStatusResponse execute(RegisterConversationStatusCommand command) {
        ConversationStatus aggregate = ConversationStatus.register(
                command.nameStatus());
        ConversationStatus saved = repository.save(aggregate);
        return new ConversationStatusResponse(
                saved.id().value(),
                saved.nameStatus(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
