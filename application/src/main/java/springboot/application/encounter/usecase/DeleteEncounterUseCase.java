package springboot.application.encounter.usecase;

import java.time.LocalDateTime;

import springboot.application.encounter.exception.EncounterNotFoundApplicationException;
import springboot.domain.encounter.event.EncounterDeletedEvent;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.encounter.port.repository.EncounterRepository;

public class DeleteEncounterUseCase {
    private final EncounterRepository repository;
    public DeleteEncounterUseCase(EncounterRepository repository) { this.repository = repository; }

    public EncounterDeletedEvent execute(EncounterId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new EncounterDeletedEvent(id, LocalDateTime.now());
    }
}
