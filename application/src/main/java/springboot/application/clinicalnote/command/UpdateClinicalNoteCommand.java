package springboot.application.clinicalnote.command;

import java.util.Objects;
import java.time.LocalDateTime;

import springboot.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public record UpdateClinicalNoteCommand(
        ClinicalNoteId id,
        EncounterId encounterId,
        ProfessionalId professionalId,
        String subjective,
        String objective,
        String assessment,
        String plan,
        String additionalNotes,
        LocalDateTime signedAt
) {
    public UpdateClinicalNoteCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(encounterId, "encounterId must not be null");
        Objects.requireNonNull(professionalId, "professionalId must not be null");
        Objects.requireNonNull(subjective, "subjective must not be null");
        Objects.requireNonNull(objective, "objective must not be null");
        Objects.requireNonNull(assessment, "assessment must not be null");
        Objects.requireNonNull(plan, "plan must not be null");
        Objects.requireNonNull(additionalNotes, "additionalNotes must not be null");
        Objects.requireNonNull(signedAt, "signedAt must not be null");
    }
}
