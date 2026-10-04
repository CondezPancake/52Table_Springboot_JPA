package springboot.domain.common.exception;

import springboot.domain.conversationstatus.model.valueobject.ConversationStatusId;

public class ConversationStatusNotFoundException extends RuntimeException {
    public ConversationStatusNotFoundException(ConversationStatusId id) {
        super("ConversationStatus not found with id: " + id.value());
    }
}
