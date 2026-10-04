package springboot.application.documenttype.command;

import java.util.Objects;

import springboot.domain.documenttype.model.valueobject.DocumentTypeId;


public record UpdateDocumentTypeCommand(
        DocumentTypeId id,
        String code,
        String name,
        boolean active
) {
    public UpdateDocumentTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
