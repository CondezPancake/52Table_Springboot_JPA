package springboot.application.chatescalation.exception;

import springboot.application.common.exception.ApplicationException;

public class ChatEscalationNotFoundApplicationException extends ApplicationException {
    public ChatEscalationNotFoundApplicationException(String message) {
        super(message);
    }
}
