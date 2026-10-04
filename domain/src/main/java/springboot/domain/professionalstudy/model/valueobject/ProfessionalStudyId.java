package springboot.domain.professionalstudy.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ProfessionalStudyId(UUID value) {
    public ProfessionalStudyId {
        Objects.requireNonNull(value, "ProfessionalStudyId value must not be null");
    }

    public static ProfessionalStudyId generate() {
        return new ProfessionalStudyId(UUID.randomUUID());
    }
}
