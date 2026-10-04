package springboot.application.chatescalationassignment.exception;

import springboot.application.common.exception.ApplicationException;

public class ChatEscalationAssignmentNotFoundApplicationException extends ApplicationException {
    public ChatEscalationAssignmentNotFoundApplicationException(String message) {
        super(message);
    }
}
