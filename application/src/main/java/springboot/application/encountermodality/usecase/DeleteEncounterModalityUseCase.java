package springboot.application.encountermodality.usecase;

import java.time.LocalDateTime;

import springboot.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import springboot.domain.encountermodality.event.EncounterModalityDeletedEvent;
import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;
import springboot.domain.encountermodality.port.repository.EncounterModalityRepository;

public class DeleteEncounterModalityUseCase {
    private final EncounterModalityRepository repository;
    public DeleteEncounterModalityUseCase(EncounterModalityRepository repository) { this.repository = repository; }

    public EncounterModalityDeletedEvent execute(EncounterModalityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterModalityNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new EncounterModalityDeletedEvent(id, LocalDateTime.now());
    }
}
