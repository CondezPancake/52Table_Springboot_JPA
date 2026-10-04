package springboot.application.encounterstatus.usecase;

import springboot.application.encounterstatus.command.RegisterEncounterStatusCommand;
import springboot.application.encounterstatus.dto.EncounterStatusResponse;
import springboot.domain.encounterstatus.model.aggregate.EncounterStatus;
import springboot.domain.encounterstatus.port.repository.EncounterStatusRepository;

public class RegisterEncounterStatusUseCase {
    private final EncounterStatusRepository repository;
    public RegisterEncounterStatusUseCase(EncounterStatusRepository repository) { this.repository = repository; }

    public EncounterStatusResponse execute(RegisterEncounterStatusCommand command) {
        EncounterStatus aggregate = EncounterStatus.register(
                command.code(),
                command.name(),
                command.active());
        EncounterStatus saved = repository.save(aggregate);
        return new EncounterStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
