package springboot.application.diagnosticsystem.usecase;

import java.time.LocalDateTime;

import springboot.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import springboot.domain.diagnosticsystem.event.DiagnosticSystemDeletedEvent;
import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import springboot.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class DeleteDiagnosticSystemUseCase {
    private final DiagnosticSystemRepository repository;
    public DeleteDiagnosticSystemUseCase(DiagnosticSystemRepository repository) { this.repository = repository; }

    public DiagnosticSystemDeletedEvent execute(DiagnosticSystemId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DiagnosticSystemNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new DiagnosticSystemDeletedEvent(id, LocalDateTime.now());
    }
}
