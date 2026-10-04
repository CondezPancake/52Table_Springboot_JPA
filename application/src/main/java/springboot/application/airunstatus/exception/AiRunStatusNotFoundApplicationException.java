package springboot.application.airunstatus.exception;

import springboot.application.common.exception.ApplicationException;

public class AiRunStatusNotFoundApplicationException extends ApplicationException {
    public AiRunStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
