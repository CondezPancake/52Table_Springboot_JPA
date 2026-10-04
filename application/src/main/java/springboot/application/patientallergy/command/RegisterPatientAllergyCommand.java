package springboot.application.patientallergy.command;

import java.util.Objects;
import java.time.LocalDateTime;

import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record RegisterPatientAllergyCommand(
        PatientId patientId,
        String substance,
        String reaction,
        String severity,
        boolean active,
        LocalDateTime recordedAt,
        ProfessionalId recordedBy
) {
    public RegisterPatientAllergyCommand {
        Objects.requireNonNull(patientId, "patientId must not be null");
        Objects.requireNonNull(substance, "substance must not be null");
        Objects.requireNonNull(severity, "severity must not be null");
        Objects.requireNonNull(recordedAt, "recordedAt must not be null");
        Objects.requireNonNull(recordedBy, "recordedBy must not be null");
    }
}
