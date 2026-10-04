package springboot.domain.common.exception;

import springboot.domain.escalationstatus.model.valueobject.EscalationStatusId;

public class EscalationStatusNotFoundException extends RuntimeException {
    public EscalationStatusNotFoundException(EscalationStatusId id) {
        super("EscalationStatus not found with id: " + id.value());
    }
}
