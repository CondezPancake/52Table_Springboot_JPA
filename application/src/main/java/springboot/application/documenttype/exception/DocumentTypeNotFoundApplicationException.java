package springboot.application.documenttype.exception;

import springboot.application.common.exception.ApplicationException;

public class DocumentTypeNotFoundApplicationException extends ApplicationException {
    public DocumentTypeNotFoundApplicationException(String message) {
        super(message);
    }
}
