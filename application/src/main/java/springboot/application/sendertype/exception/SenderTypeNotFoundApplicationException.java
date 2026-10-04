package springboot.application.sendertype.exception;

import springboot.application.common.exception.ApplicationException;

public class SenderTypeNotFoundApplicationException extends ApplicationException {
    public SenderTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
