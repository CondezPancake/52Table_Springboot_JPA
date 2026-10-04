package springboot.domain.common.exception;

import springboot.domain.diagnosticsystem.model.valueobject.DiagnosticSystemId;

public class DiagnosticSystemNotFoundException extends RuntimeException {
    public DiagnosticSystemNotFoundException(DiagnosticSystemId id) {
        super("DiagnosticSystem not found with id: " + id.value());
    }
}
