package springboot.application.encountermodality.usecase;

import springboot.application.encountermodality.command.UpdateEncounterModalityCommand;
import springboot.application.encountermodality.dto.EncounterModalityResponse;
import springboot.application.encountermodality.exception.EncounterModalityNotFoundApplicationException;
import springboot.domain.encountermodality.port.repository.EncounterModalityRepository;

public class UpdateEncounterModalityUseCase {
    private final EncounterModalityRepository repository;
    public UpdateEncounterModalityUseCase(EncounterModalityRepository repository) { this.repository = repository; }

    public EncounterModalityResponse execute(UpdateEncounterModalityCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EncounterModalityNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active());
        var saved = repository.save(aggregate);
        return new EncounterModalityResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
