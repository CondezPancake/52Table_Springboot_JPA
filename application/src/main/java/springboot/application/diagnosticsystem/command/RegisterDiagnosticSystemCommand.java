package springboot.application.diagnosticsystem.command;

import java.util.Objects;




public record RegisterDiagnosticSystemCommand(
        String code,
        String name,
        boolean active,
        String version
) {
    public RegisterDiagnosticSystemCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(version, "version must not be null");
    }
}
