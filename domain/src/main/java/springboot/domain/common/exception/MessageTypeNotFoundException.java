package springboot.domain.common.exception;

import springboot.domain.messagetype.model.valueobject.MessageTypeId;

public class MessageTypeNotFoundException extends RuntimeException {
    public MessageTypeNotFoundException(MessageTypeId id) {
        super("MessageType not found with id: " + id.value());
    }
}
