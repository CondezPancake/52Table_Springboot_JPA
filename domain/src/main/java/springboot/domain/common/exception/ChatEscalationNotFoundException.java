package springboot.domain.common.exception;

import springboot.domain.chatescalation.model.valueobject.ChatEscalationId;

public class ChatEscalationNotFoundException extends RuntimeException {
    public ChatEscalationNotFoundException(ChatEscalationId id) {
        super("ChatEscalation not found with id: " + id.value());
    }
}
