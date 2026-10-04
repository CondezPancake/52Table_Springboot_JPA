package springboot.application.escalationstatus.usecase;

import springboot.application.escalationstatus.command.RegisterEscalationStatusCommand;
import springboot.application.escalationstatus.dto.EscalationStatusResponse;
import springboot.domain.escalationstatus.model.aggregate.EscalationStatus;
import springboot.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class RegisterEscalationStatusUseCase {
    private final EscalationStatusRepository repository;
    public RegisterEscalationStatusUseCase(EscalationStatusRepository repository) { this.repository = repository; }

    public EscalationStatusResponse execute(RegisterEscalationStatusCommand command) {
        EscalationStatus aggregate = EscalationStatus.register(
                command.nameStatus());
        EscalationStatus saved = repository.save(aggregate);
        return new EscalationStatusResponse(
                saved.id().value(),
                saved.nameStatus(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
