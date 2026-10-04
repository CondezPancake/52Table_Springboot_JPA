package springboot.application.encountertype.command;

import java.util.Objects;




public record RegisterEncounterTypeCommand(
        String code,
        String name,
        boolean active
) {
    public RegisterEncounterTypeCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
