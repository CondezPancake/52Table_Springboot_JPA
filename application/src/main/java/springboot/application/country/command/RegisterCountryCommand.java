package springboot.application.country.command;

import java.util.Objects;



public record RegisterCountryCommand(
        String nameCountry,
        String codeCountry,
        String description,
        boolean active,
        String telephonePrefix
) {
    public RegisterCountryCommand {
        Objects.requireNonNull(nameCountry, "nameCountry must not be null");
        Objects.requireNonNull(codeCountry, "codeCountry must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(telephonePrefix, "telephonePrefix must not be null");
    }
}
