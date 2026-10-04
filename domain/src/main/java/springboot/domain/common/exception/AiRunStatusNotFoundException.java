package springboot.domain.common.exception;

import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;

public class AiRunStatusNotFoundException extends RuntimeException {
    public AiRunStatusNotFoundException(AiRunStatusId id) {
        super("AiRunStatus not found with id: " + id.value());
    }
}
