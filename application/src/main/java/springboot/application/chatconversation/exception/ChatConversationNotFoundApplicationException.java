package springboot.application.chatconversation.exception;

import springboot.application.common.exception.ApplicationException;

public class ChatConversationNotFoundApplicationException extends ApplicationException {
    public ChatConversationNotFoundApplicationException(String message) {
        super(message);
    }
}
