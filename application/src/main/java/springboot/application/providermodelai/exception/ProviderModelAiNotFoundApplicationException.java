package springboot.application.providermodelai.exception;

import springboot.application.common.exception.ApplicationException;

public class ProviderModelAiNotFoundApplicationException extends ApplicationException {
    public ProviderModelAiNotFoundApplicationException(String message) {
        super(message);
    }
}
