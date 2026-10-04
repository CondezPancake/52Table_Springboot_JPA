package springboot.application.encounterstatus.usecase;

import java.time.LocalDateTime;

import springboot.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import springboot.domain.encounterstatus.event.EncounterStatusDeletedEvent;
import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;
import springboot.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class DeleteEncounterStatusUseCase {
    private final EncounterStatusRepository repository;
    public DeleteEncounterStatusUseCase(EncounterStatusRepository repository) { this.repository = repository; }

    public EncounterStatusDeletedEvent execute(EncounterStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new EncounterStatusDeletedEvent(id, LocalDateTime.now());
    }
}
