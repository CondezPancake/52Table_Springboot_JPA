package springboot.application.diagnosticsystem.command;

import java.util.Objects;


import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;


public record UpdateDiagnosticSystemCommand(
        DiagnosticSystemId id,
        String code,
        String name,
        boolean active,
        String version
) {
    public UpdateDiagnosticSystemCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(version, "version must not be null");
    }
}
