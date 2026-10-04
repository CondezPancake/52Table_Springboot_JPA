package springboot.application.chatescalationstatushistory.usecase;

import java.time.LocalDateTime;

import springboot.application.chatescalationstatushistory.exception.ChatEscalationStatusHistoryNotFoundApplicationException;
import springboot.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryDeletedEvent;
import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import springboot.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;

public class DeleteChatEscalationStatusHistoryUseCase {
    private final ChatEscalationStatusHistoryRepository repository;
    public DeleteChatEscalationStatusHistoryUseCase(ChatEscalationStatusHistoryRepository repository) { this.repository = repository; }

    public ChatEscalationStatusHistoryDeletedEvent execute(ChatEscalationStatusHistoryId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ChatEscalationStatusHistoryNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ChatEscalationStatusHistoryDeletedEvent(id, LocalDateTime.now());
    }
}
