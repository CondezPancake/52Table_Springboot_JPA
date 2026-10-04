package springboot.application.conversationstatus.exception;

import springboot.application.common.exception.ApplicationException;

public class ConversationStatusNotFoundApplicationException extends ApplicationException {
    public ConversationStatusNotFoundApplicationException(String message) {
        super(message);
    }
}
