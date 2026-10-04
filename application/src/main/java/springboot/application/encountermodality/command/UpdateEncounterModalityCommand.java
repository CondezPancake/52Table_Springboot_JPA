package springboot.application.encountermodality.command;

import java.util.Objects;


import springboot.domain.encountermodality.model.valueobject.EncounterModalityId;


public record UpdateEncounterModalityCommand(
        EncounterModalityId id,
        String code,
        String name,
        boolean active
) {
    public UpdateEncounterModalityCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
