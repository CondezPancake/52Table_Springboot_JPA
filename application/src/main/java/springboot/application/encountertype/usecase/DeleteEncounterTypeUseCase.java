package springboot.application.encountertype.usecase;

import java.time.LocalDateTime;

import springboot.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import springboot.domain.encountertype.event.EncounterTypeDeletedEvent;
import springboot.domain.encountertype.model.valueobject.EncounterTypeId;
import springboot.domain.encountertype.port.repository.EncounterTypeRepository;

public class DeleteEncounterTypeUseCase {
    private final EncounterTypeRepository repository;
    public DeleteEncounterTypeUseCase(EncounterTypeRepository repository) { this.repository = repository; }

    public EncounterTypeDeletedEvent execute(EncounterTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EncounterTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new EncounterTypeDeletedEvent(id, LocalDateTime.now());
    }
}
