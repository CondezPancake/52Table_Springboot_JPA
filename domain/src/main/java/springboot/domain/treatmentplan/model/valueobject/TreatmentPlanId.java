package springboot.domain.treatmentplan.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record TreatmentPlanId(UUID value) {
    public TreatmentPlanId {
        Objects.requireNonNull(value, "TreatmentPlanId value must not be null");
    }

    public static TreatmentPlanId generate() {
        return new TreatmentPlanId(UUID.randomUUID());
    }
}
