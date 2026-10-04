package springboot.domain.common.exception;

import springboot.domain.priority.model.valueobject.PriorityId;

public class PriorityNotFoundException extends RuntimeException {
    public PriorityNotFoundException(PriorityId id) {
        super("Priority not found with id: " + id.value());
    }
}
