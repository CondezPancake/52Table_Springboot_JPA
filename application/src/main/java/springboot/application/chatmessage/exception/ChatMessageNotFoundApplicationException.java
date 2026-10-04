package springboot.application.chatmessage.exception;

import springboot.application.common.exception.ApplicationException;

public class ChatMessageNotFoundApplicationException extends ApplicationException {
    public ChatMessageNotFoundApplicationException(String message) {
        super(message);
    }
}
