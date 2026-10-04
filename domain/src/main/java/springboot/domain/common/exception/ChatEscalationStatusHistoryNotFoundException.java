package springboot.domain.common.exception;

import springboot.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public class ChatEscalationStatusHistoryNotFoundException extends RuntimeException {
    public ChatEscalationStatusHistoryNotFoundException(ChatEscalationStatusHistoryId id) {
        super("ChatEscalationStatusHistory not found with id: " + id.value());
    }
}
