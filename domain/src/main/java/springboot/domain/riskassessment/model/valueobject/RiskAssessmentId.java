package springboot.domain.riskassessment.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record RiskAssessmentId(UUID value) {
    public RiskAssessmentId {
        Objects.requireNonNull(value, "RiskAssessmentId value must not be null");
    }

    public static RiskAssessmentId generate() {
        return new RiskAssessmentId(UUID.randomUUID());
    }
}
