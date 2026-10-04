package springboot.application.encountertype.usecase;

import springboot.application.encountertype.command.RegisterEncounterTypeCommand;
import springboot.application.encountertype.dto.EncounterTypeResponse;
import springboot.domain.encountertype.model.aggregate.EncounterType;
import springboot.domain.encountertype.port.repository.EncounterTypeRepository;

public class RegisterEncounterTypeUseCase {
    private final EncounterTypeRepository repository;
    public RegisterEncounterTypeUseCase(EncounterTypeRepository repository) { this.repository = repository; }

    public EncounterTypeResponse execute(RegisterEncounterTypeCommand command) {
        EncounterType aggregate = EncounterType.register(
                command.code(),
                command.name(),
                command.active());
        EncounterType saved = repository.save(aggregate);
        return new EncounterTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
