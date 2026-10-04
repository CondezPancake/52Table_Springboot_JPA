package springboot.domain.professionaltype.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ProfessionalTypeId(UUID value) {
    public ProfessionalTypeId {
        Objects.requireNonNull(value, "ProfessionalTypeId value must not be null");
    }

    public static ProfessionalTypeId generate() {
        return new ProfessionalTypeId(UUID.randomUUID());
    }
}
