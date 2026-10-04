package springboot.domain.mentalstatusexam.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record MentalStatusExamId(UUID value) {
    public MentalStatusExamId {
        Objects.requireNonNull(value, "MentalStatusExamId value must not be null");
    }

    public static MentalStatusExamId generate() {
        return new MentalStatusExamId(UUID.randomUUID());
    }
}
