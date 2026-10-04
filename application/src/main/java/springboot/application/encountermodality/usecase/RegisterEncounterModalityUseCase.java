package springboot.application.encountermodality.usecase;

import springboot.application.encountermodality.command.RegisterEncounterModalityCommand;
import springboot.application.encountermodality.dto.EncounterModalityResponse;
import springboot.domain.encountermodality.model.aggregate.EncounterModality;
import springboot.domain.encountermodality.port.repository.EncounterModalityRepository;

public class RegisterEncounterModalityUseCase {
    private final EncounterModalityRepository repository;
    public RegisterEncounterModalityUseCase(EncounterModalityRepository repository) { this.repository = repository; }

    public EncounterModalityResponse execute(RegisterEncounterModalityCommand command) {
        EncounterModality aggregate = EncounterModality.register(
                command.code(),
                command.name(),
                command.active());
        EncounterModality saved = repository.save(aggregate);
        return new EncounterModalityResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
