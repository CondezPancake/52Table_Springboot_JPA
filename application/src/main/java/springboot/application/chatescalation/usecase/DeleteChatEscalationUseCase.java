package springboot.application.chatescalation.usecase;

import java.time.LocalDateTime;

import springboot.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import springboot.domain.chatescalation.event.ChatEscalationDeletedEvent;
import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;
import springboot.domain.chatescalation.port.repository.ChatEscalationRepository;

public class DeleteChatEscalationUseCase {
    private final ChatEscalationRepository repository;
    public DeleteChatEscalationUseCase(ChatEscalationRepository repository) { this.repository = repository; }

    public ChatEscalationDeletedEvent execute(ChatEscalationId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ChatEscalationDeletedEvent(id, LocalDateTime.now());
    }
}
