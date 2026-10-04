package springboot.application.chatconversationaisetting.exception;

import springboot.application.common.exception.ApplicationException;

public class ChatConversationAiSettingNotFoundApplicationException extends ApplicationException {
    public ChatConversationAiSettingNotFoundApplicationException(String message) {
        super(message);
    }
}
