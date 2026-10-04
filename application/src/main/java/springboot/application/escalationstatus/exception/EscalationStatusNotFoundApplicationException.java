package springboot.application.escalationstatus.exception;

import springboot.application.common.exception.ApplicationException;

public class EscalationStatusNotFoundApplicationException extends ApplicationException {
    public EscalationStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
