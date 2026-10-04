package springboot.application.airunstatus.usecase;

import java.time.LocalDateTime;

import springboot.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import springboot.domain.airunstatus.event.AiRunStatusDeletedEvent;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;
import springboot.domain.airunstatus.port.repository.AiRunStatusRepository;

public class DeleteAiRunStatusUseCase {
    private final AiRunStatusRepository repository;
    public DeleteAiRunStatusUseCase(AiRunStatusRepository repository) { this.repository = repository; }

    public AiRunStatusDeletedEvent execute(AiRunStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AiRunStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new AiRunStatusDeletedEvent(id, LocalDateTime.now());
    }
}
