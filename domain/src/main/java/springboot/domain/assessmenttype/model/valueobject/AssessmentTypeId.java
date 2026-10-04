package springboot.domain.assessmenttype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record AssessmentTypeId(UUID value) {
    public AssessmentTypeId {
        Objects.requireNonNull(value, "AssessmentTypeId value must not be null");
    }

    public static AssessmentTypeId generate() {
        return new AssessmentTypeId(UUID.randomUUID());
    }
}
