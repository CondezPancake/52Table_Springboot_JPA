package springboot.application.priority.exception;

import springboot.application.common.exception.ApplicationException;

public class PriorityNotFoundApplicationException extends ApplicationException {
    public PriorityNotFoundApplicationException(String message) {
        super(message);
    }
}
