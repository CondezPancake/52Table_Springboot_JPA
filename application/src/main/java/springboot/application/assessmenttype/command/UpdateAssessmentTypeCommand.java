package springboot.application.assessmenttype.command;

import java.util.Objects;


import springboot.domain.assessmenttype.model.valueobject.AssessmentTypeId;


public record UpdateAssessmentTypeCommand(
        AssessmentTypeId id,
        String code,
        String name,
        boolean active,
        String description
) {
    public UpdateAssessmentTypeCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(code, "code must not be null");
        Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(description, "description must not be null");
    }
}
