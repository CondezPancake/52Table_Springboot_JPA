package springboot.application.consenttype.exception;

import springboot.application.common.exception.ApplicationException;

public class ConsentTypeNotFoundApplicationException extends ApplicationException {
    public ConsentTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
