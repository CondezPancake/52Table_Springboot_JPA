package springboot.application.encounterstatus.command;

import java.util.Objects;


import springboot.domain.encounterstatus.model.valueobject.EncounterStatusId;


public record UpdateEncounterStatusCommand(
        EncounterStatusId id,
        String code,
        String name,
        boolean active
) {
    public UpdateEncounterStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
