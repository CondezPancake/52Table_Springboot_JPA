package springboot.application.country.command;

import java.util.Objects;

import springboot.domain.country.model.valueobject.CountryId;


public record UpdateCountryCommand(
        CountryId id,
        String nameCountry,
        String codeCountry,
        String description,
        boolean active,
        String telephonePrefix
) {
    public UpdateCountryCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameCountry, "nameCountry must not be null");
        Objects.requireNonNull(codeCountry, "codeCountry must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(telephonePrefix, "telephonePrefix must not be null");
    }
}
