package springboot.application.priority.usecase;

import java.time.LocalDateTime;

import springboot.application.priority.exception.PriorityNotFoundApplicationException;
import springboot.domain.priority.event.PriorityDeletedEvent;
import springboot.domain.priority.model.valueobject.PriorityId;
import springboot.domain.priority.port.repository.PriorityRepository;

public class DeletePriorityUseCase {
    private final PriorityRepository repository;
    public DeletePriorityUseCase(PriorityRepository repository) { this.repository = repository; }

    public PriorityDeletedEvent execute(PriorityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PriorityNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new PriorityDeletedEvent(id, LocalDateTime.now());
    }
}
