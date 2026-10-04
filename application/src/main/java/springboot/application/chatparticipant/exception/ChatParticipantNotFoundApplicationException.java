package springboot.application.chatparticipant.exception;

import springboot.application.common.exception.ApplicationException;

public class ChatParticipantNotFoundApplicationException extends ApplicationException {
    public ChatParticipantNotFoundApplicationException(String message) {
        super(message);
    }
}
