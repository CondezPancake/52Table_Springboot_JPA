package springboot.application.assessmenttype.command;

import java.util.Objects;




public record RegisterAssessmentTypeCommand(
        String code,
        String name,
        boolean active,
        String description
) {
    public RegisterAssessmentTypeCommand {
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(description, "description must not be null");
    }
}
