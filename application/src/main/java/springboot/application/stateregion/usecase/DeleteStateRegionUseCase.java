package springboot.application.stateregion.usecase;

import java.time.LocalDateTime;

import springboot.application.stateregion.exception.StateRegionNotFoundApplicationException;
import springboot.domain.stateregion.event.StateRegionDeletedEvent;
import springboot.domain.stateregion.model.valueobject.StateRegionId;
import springboot.domain.stateregion.port.repository.StateRegionRepository;

public class DeleteStateRegionUseCase {
    private final StateRegionRepository repository;
    public DeleteStateRegionUseCase(StateRegionRepository repository) { this.repository = repository; }

    public StateRegionDeletedEvent execute(StateRegionId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new StateRegionNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new StateRegionDeletedEvent(id, LocalDateTime.now());
    }
}
