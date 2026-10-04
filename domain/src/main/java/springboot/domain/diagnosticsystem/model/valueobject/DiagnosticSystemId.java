package springboot.domain.diagnosticsystem.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record DiagnosticSystemId(UUID value) {
    public DiagnosticSystemId {
        Objects.requireNonNull(value, "DiagnosticSystemId value must not be null");
    }

    public static DiagnosticSystemId generate() {
        return new DiagnosticSystemId(UUID.randomUUID());
    }
}
