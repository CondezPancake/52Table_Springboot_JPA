package springboot.application.chatescalationstatushistory.exception;

import springboot.application.common.exception.ApplicationException;

public class ChatEscalationStatusHistoryNotFoundApplicationException extends ApplicationException {
    public ChatEscalationStatusHistoryNotFoundApplicationException(String message) {
        super(message);
    }
}
