package springboot.application.conversationstatus.usecase;

import java.time.LocalDateTime;

import springboot.application.conversationstatus.exception.ConversationStatusNotFoundApplicationException;
import springboot.domain.conversationstatus.event.ConversationStatusDeletedEvent;
import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;
import springboot.domain.conversationstatus.port.repository.ConversationStatusRepository;

public class DeleteConversationStatusUseCase {
    private final ConversationStatusRepository repository;
    public DeleteConversationStatusUseCase(ConversationStatusRepository repository) { this.repository = repository; }

    public ConversationStatusDeletedEvent execute(ConversationStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ConversationStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ConversationStatusDeletedEvent(id, LocalDateTime.now());
    }
}
