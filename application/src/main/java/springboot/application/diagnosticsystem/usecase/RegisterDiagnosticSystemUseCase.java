package springboot.application.diagnosticsystem.usecase;

import springboot.application.diagnosticsystem.command.RegisterDiagnosticSystemCommand;
import springboot.application.diagnosticsystem.dto.DiagnosticSystemResponse;
import springboot.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;
import springboot.domain.diagnosticsystem.port.repository.DiagnosticSystemRepository;

public class RegisterDiagnosticSystemUseCase {
    private final DiagnosticSystemRepository repository;
    public RegisterDiagnosticSystemUseCase(DiagnosticSystemRepository repository) { this.repository = repository; }

    public DiagnosticSystemResponse execute(RegisterDiagnosticSystemCommand command) {
        DiagnosticSystem aggregate = DiagnosticSystem.register(
                command.code(),
                command.name(),
                command.active(),
                command.version());
        DiagnosticSystem saved = repository.save(aggregate);
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
