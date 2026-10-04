package springboot.domain.treatmentgoal.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record TreatmentGoalId(UUID value) {
    public TreatmentGoalId {
        Objects.requireNonNull(value, "TreatmentGoalId value must not be null");
    }

    public static TreatmentGoalId generate() {
        return new TreatmentGoalId(UUID.randomUUID());
    }
}
