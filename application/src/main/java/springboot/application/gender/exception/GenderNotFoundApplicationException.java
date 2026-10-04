package springboot.application.gender.exception;

import springboot.application.common.exception.ApplicationException;

public class GenderNotFoundApplicationException extends ApplicationException {
    public GenderNotFoundApplicationException(String message) {
        super(message);
    }
}
