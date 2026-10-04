package springboot.domain.study.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record StudyId(UUID value) {
    public StudyId {
        Objects.requireNonNull(value, "StudyId value must not be null");
    }

    public static StudyId generate() {
        return new StudyId(UUID.randomUUID());
    }
}
