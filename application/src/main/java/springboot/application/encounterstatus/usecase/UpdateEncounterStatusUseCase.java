package springboot.application.encounterstatus.usecase;

import springboot.application.encounterstatus.command.UpdateEncounterStatusCommand;
import springboot.application.encounterstatus.dto.EncounterStatusResponse;
import springboot.application.encounterstatus.exception.EncounterStatusNotFoundApplicationException;
import springboot.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class UpdateEncounterStatusUseCase {
    private final EncounterStatusRepository repository;
    public UpdateEncounterStatusUseCase(EncounterStatusRepository repository) { this.repository = repository; }

    public EncounterStatusResponse execute(UpdateEncounterStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EncounterStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active());
        var saved = repository.save(aggregate);
        return new EncounterStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
