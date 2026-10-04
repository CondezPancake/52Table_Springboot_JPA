package springboot.application.encountertype.usecase;

import springboot.application.encountertype.command.UpdateEncounterTypeCommand;
import springboot.application.encountertype.dto.EncounterTypeResponse;
import springboot.application.encountertype.exception.EncounterTypeNotFoundApplicationException;
import springboot.domain.encountertype.port.repository.EncounterTypeRepository;

public class UpdateEncounterTypeUseCase {
    private final EncounterTypeRepository repository;
    public UpdateEncounterTypeUseCase(EncounterTypeRepository repository) { this.repository = repository; }

    public EncounterTypeResponse execute(UpdateEncounterTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EncounterTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active());
        var saved = repository.save(aggregate);
        return new EncounterTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
