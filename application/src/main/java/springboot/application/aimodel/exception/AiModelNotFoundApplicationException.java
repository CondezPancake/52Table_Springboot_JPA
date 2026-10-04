package springboot.application.aimodel.exception;

import springboot.application.common.exception.ApplicationException;

public class AiModelNotFoundApplicationException extends ApplicationException {
    public AiModelNotFoundApplicationException(String message) {
        super(message);
    }
}
