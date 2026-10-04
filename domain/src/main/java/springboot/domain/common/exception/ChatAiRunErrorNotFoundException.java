package springboot.domain.common.exception;

import springboot.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

public class ChatAiRunErrorNotFoundException extends RuntimeException {
    public ChatAiRunErrorNotFoundException(ChatAiRunErrorId id) {
        super("ChatAiRunError not found with id: " + id.value());
    }
}
