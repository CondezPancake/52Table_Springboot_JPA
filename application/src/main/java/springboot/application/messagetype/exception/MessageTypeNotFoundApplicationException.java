package springboot.application.messagetype.exception;

import springboot.application.common.exception.ApplicationException;

public class MessageTypeNotFoundApplicationException extends ApplicationException {
    public MessageTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
