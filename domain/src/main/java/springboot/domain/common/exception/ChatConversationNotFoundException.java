package springboot.domain.common.exception;

import springboot.domain.chatconversation.model.valueobject.ChatConversationId;

public class ChatConversationNotFoundException extends RuntimeException {
    public ChatConversationNotFoundException(ChatConversationId id) {
        super("ChatConversation not found with id: " + id.value());
    }
}
