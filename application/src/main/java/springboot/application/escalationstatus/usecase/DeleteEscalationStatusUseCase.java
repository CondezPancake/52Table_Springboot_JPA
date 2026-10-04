package springboot.application.escalationstatus.usecase;

import java.time.LocalDateTime;

import springboot.application.escalationstatus.exception.EscalationStatusNotFoundApplicationException;
import springboot.domain.escalationstatus.event.EscalationStatusDeletedEvent;
import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;
import springboot.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class DeleteEscalationStatusUseCase {
    private final EscalationStatusRepository repository;
    public DeleteEscalationStatusUseCase(EscalationStatusRepository repository) { this.repository = repository; }

    public EscalationStatusDeletedEvent execute(EscalationStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new EscalationStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new EscalationStatusDeletedEvent(id, LocalDateTime.now());
    }
}
