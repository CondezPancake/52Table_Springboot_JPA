package springboot.application.risklevel.command;

import java.util.Objects;




public record RegisterRiskLevelCommand(
        String code,
        String name,
        boolean active,
        int severity
) {
    public RegisterRiskLevelCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
    }
}
