package springboot.application.diagnosticsystem.exception;

import springboot.application.common.exception.ApplicationException;

public class DiagnosticSystemNotFoundApplicationException extends ApplicationException {
    public DiagnosticSystemNotFoundApplicationException(String message) {
        super(message);
    }
}
