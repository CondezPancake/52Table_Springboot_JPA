package springboot.domain.documenttype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record DocumentTypeId(UUID value) {
    public DocumentTypeId {
        Objects.requireNonNull(value, "DocumentTypeId value must not be null");
    }

    public static DocumentTypeId generate() {
        return new DocumentTypeId(UUID.randomUUID());
    }
}
