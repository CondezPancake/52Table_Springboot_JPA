package springboot.application.encountertype.command;

import java.util.Objects;


import springboot.domain.encountertype.model.valueobject.EncounterTypeId;


public record UpdateEncounterTypeCommand(
        EncounterTypeId id,
        String code,
        String name,
        boolean active
) {
    public UpdateEncounterTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
