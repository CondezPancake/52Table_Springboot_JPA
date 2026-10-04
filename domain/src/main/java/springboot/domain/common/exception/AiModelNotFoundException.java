package springboot.domain.common.exception;

import springboot.domain.aimodel.model.valueobject.AiModelId;

public class AiModelNotFoundException extends RuntimeException {
    public AiModelNotFoundException(AiModelId id) {
        super("AiModel not found with id: " + id.value());
    }
}
