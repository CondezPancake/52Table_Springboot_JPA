package springboot.domain.common.exception;

import springboot.domain.providermodelai.model.valueobject.ProviderModelAiId;

public class ProviderModelAiNotFoundException extends RuntimeException {
    public ProviderModelAiNotFoundException(ProviderModelAiId id) {
        super("ProviderModelAi not found with id: " + id.value());
    }
}
