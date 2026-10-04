package springboot.application.diagnosticsystem.usecase;

import springboot.application.diagnosticsystem.command.UpdateDiagnosticSystemCommand;
import springboot.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import springboot.application.diagnosticsystem.exception.DiagnosticSystemNotFoundApplicationException;
import springboot.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class UpdateDiagnosticSystemUseCase {
    private final DiagnosticSystemRepository repository;
    public UpdateDiagnosticSystemUseCase(DiagnosticSystemRepository repository) { this.repository = repository; }

    public DiagnosticSystemResponse execute(UpdateDiagnosticSystemCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new DiagnosticSystemNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active(),
                command.version());
        var saved = repository.save(aggregate);
        return new DiagnosticSystemResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.version(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
