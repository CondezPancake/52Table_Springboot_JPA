package springboot.application.diagnosticsystem.usecase;

import springboot.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import springboot.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;
import springboot.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class GetDiagnosticSystemByIdUseCase {
    private final DiagnosticSystemRepository repository;
    public GetDiagnosticSystemByIdUseCase(DiagnosticSystemRepository repository) { this.repository = repository; }

    public DiagnosticSystemResponse execute(DiagnosticSystemId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new DiagnosticSystemNotFoundApplicationException(id.value().toString()));
        return new DiagnosticSystemResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.version(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
