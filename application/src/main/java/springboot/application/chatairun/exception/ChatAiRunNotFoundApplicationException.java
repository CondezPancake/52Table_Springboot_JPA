package springboot.application.chatairun.exception;

import springboot.application.common.exception.ApplicationException;

public class ChatAiRunNotFoundApplicationException extends ApplicationException {
    public ChatAiRunNotFoundApplicationException(String message) {
        super(message);
    }
}
