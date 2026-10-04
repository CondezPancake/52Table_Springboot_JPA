package springboot.application.escalationstatus.usecase;

import springboot.application.escalationstatus.command.UpdateEscalationStatusCommand;
import springboot.application.escalationstatus.dto.EscalationStatusResponse;
import springboot.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import springboot.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class UpdateEscalationStatusUseCase {
    private final EscalationStatusRepository repository;
    public UpdateEscalationStatusUseCase(EscalationStatusRepository repository) { this.repository = repository; }

    public EscalationStatusResponse execute(UpdateEscalationStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new EscalationStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.nameStatus());
        var saved = repository.save(aggregate);
        return new EscalationStatusResponse(
                saved.id().value(),
                saved.nameStatus(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
