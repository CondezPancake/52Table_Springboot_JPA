package springboot.domain.common.exception;

import springboot.domain.sendertype.model.valueobject.SenderTypeId;

public class SenderTypeNotFoundException extends RuntimeException {
    public SenderTypeNotFoundException(SenderTypeId id) {
        super("SenderType not found with id: " + id.value());
    }
}
