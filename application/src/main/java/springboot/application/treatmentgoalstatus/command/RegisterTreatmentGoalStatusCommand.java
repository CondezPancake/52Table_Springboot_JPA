package springboot.application.treatmentgoalstatus.command;

import java.util.Objects;




public record RegisterTreatmentGoalStatusCommand(
        String code,
        String name,
        boolean active
) {
    public RegisterTreatmentGoalStatusCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
