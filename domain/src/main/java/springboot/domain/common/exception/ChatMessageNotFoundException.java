package springboot.domain.common.exception;

import springboot.domain.chatmessage.model.valueobject.ChatMessageId;

public class ChatMessageNotFoundException extends RuntimeException {
    public ChatMessageNotFoundException(ChatMessageId id) {
        super("ChatMessage not found with id: " + id.value());
    }
}
