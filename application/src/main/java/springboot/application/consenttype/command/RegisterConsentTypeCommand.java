package springboot.application.consenttype.command;

import java.util.Objects;




public record RegisterConsentTypeCommand(
        String code,
        String name,
        boolean active,
        String description
) {
    public RegisterConsentTypeCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(description, "description must not be null");
    }
}
