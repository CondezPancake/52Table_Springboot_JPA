package springboot.application.consenttype.command;

import java.util.Objects;


import springboot.domain.consenttype.model.valueobject.ConsentTypeId;


public record UpdateConsentTypeCommand(
        ConsentTypeId id,
        String code,
        String name,
        boolean active,
        String description
) {
    public UpdateConsentTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(description, "description must not be null");
    }
}
