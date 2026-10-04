package springboot.domain.common.exception;

import springboot.domain.documenttype.model.valueobject.DocumentTypeId;

public class DocumentTypeNotFoundException extends RuntimeException {
    public DocumentTypeNotFoundException(DocumentTypeId id) {
        super("DocumentType not found with id: " + id.value());
    }
}
