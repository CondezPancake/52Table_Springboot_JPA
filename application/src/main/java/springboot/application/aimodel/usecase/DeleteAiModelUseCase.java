package springboot.application.aimodel.usecase;

import java.time.LocalDateTime;

import springboot.application.aimodel.exception.AiModelNotFoundApplicationException;
import springboot.domain.aimodel.event.AiModelDeletedEvent;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.aimodel.port.repository.AiModelRepository;

public class DeleteAiModelUseCase {
    private final AiModelRepository repository;
    public DeleteAiModelUseCase(AiModelRepository repository) { this.repository = repository; }

    public AiModelDeletedEvent execute(AiModelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AiModelNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new AiModelDeletedEvent(id, LocalDateTime.now());
    }
}
