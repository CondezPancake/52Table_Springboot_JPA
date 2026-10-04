package springboot.application.conversationstatus.usecase;

import springboot.application.conversationstatus.command.UpdateConversationStatusCommand;
import springboot.application.conversationstatus.dto.ConversationStatusResponse;
import springboot.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import springboot.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class UpdateConversationStatusUseCase {
    private final ConversationStatusRepository repository;
    public UpdateConversationStatusUseCase(ConversationStatusRepository repository) { this.repository = repository; }

    public ConversationStatusResponse execute(UpdateConversationStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ConversationStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.nameStatus());
        var saved = repository.save(aggregate);
        return new ConversationStatusResponse(
                saved.id().value(),
                saved.nameStatus(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
