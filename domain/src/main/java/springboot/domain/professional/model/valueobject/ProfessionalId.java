package springboot.domain.professional.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record ProfessionalId(UUID value) {
    public ProfessionalId {
        Objects.requireNonNull(value, "ProfessionalId value must not be null");
    }

    public static ProfessionalId generate() {
        return new ProfessionalId(UUID.randomUUID());
    }
}
