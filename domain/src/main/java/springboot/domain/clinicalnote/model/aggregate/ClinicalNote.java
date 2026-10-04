package springboot.domain.clinicalnote.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.clinicalnote.event.ClinicalNoteRegisteredEvent;
import springboot.domain.clinicalnote.event.ClinicalNoteUpdatedEvent;
import springboot.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public class ClinicalNote extends AggregateRoot {
    private final ClinicalNoteId id;
    private EncounterId encounterId;
    private ProfessionalId professionalId;
    private String subjective;
    private String objective;
    private String assessment;
    private String plan;
    private String additionalNotes;
    private LocalDateTime signedAt;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ClinicalNote(
            ClinicalNoteId id,
            EncounterId encounterId,
            ProfessionalId professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.encounterId = Objects.requireNonNull(encounterId, "encounterId must not be null");
        this.professionalId = Objects.requireNonNull(professionalId, "professionalId must not be null");
        this.subjective = Objects.requireNonNull(subjective, "subjective must not be null");
        this.objective = Objects.requireNonNull(objective, "objective must not be null");
        this.assessment = Objects.requireNonNull(assessment, "assessment must not be null");
        this.plan = Objects.requireNonNull(plan, "plan must not be null");
        this.additionalNotes = Objects.requireNonNull(additionalNotes, "additionalNotes must not be null");
        this.signedAt = Objects.requireNonNull(signedAt, "signedAt must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ClinicalNote register(
            EncounterId encounterId,
            ProfessionalId professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt) {
        ClinicalNoteId id = ClinicalNoteId.generate();
        LocalDateTime now = LocalDateTime.now();
        ClinicalNote aggregate = new ClinicalNote(
                id,
                encounterId,
                professionalId,
                subjective,
                objective,
                assessment,
                plan,
                additionalNotes,
                signedAt,
                now,
                now);
        aggregate.recordEvent(new ClinicalNoteRegisteredEvent(id, now));
        return aggregate;
    }

    public static ClinicalNote restore(
            ClinicalNoteId id,
            EncounterId encounterId,
            ProfessionalId professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ClinicalNote(
                id,
                encounterId,
                professionalId,
                subjective,
                objective,
                assessment,
                plan,
                additionalNotes,
                signedAt,
                createdAt,
                updatedAt);
    }

    public void update(
            EncounterId encounterId,
            ProfessionalId professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt) {
        this.encounterId = Objects.requireNonNull(encounterId, "encounterId must not be null");
        this.professionalId = Objects.requireNonNull(professionalId, "professionalId must not be null");
        this.subjective = Objects.requireNonNull(subjective, "subjective must not be null");
        this.objective = Objects.requireNonNull(objective, "objective must not be null");
        this.assessment = Objects.requireNonNull(assessment, "assessment must not be null");
        this.plan = Objects.requireNonNull(plan, "plan must not be null");
        this.additionalNotes = Objects.requireNonNull(additionalNotes, "additionalNotes must not be null");
        this.signedAt = Objects.requireNonNull(signedAt, "signedAt must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ClinicalNoteUpdatedEvent(
                        this.id,
                        this.encounterId,
                        this.professionalId,
                        this.subjective,
                        this.objective,
                        this.assessment,
                        this.plan,
                        this.additionalNotes,
                        this.signedAt,
                        this.updatedAt));
    }

    public ClinicalNoteId id() {
        return id;
    }

    public EncounterId encounterId() {
        return encounterId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }

    public String subjective() {
        return subjective;
    }

    public String objective() {
        return objective;
    }

    public String assessment() {
        return assessment;
    }

    public String plan() {
        return plan;
    }

    public String additionalNotes() {
        return additionalNotes;
    }

    public LocalDateTime signedAt() {
        return signedAt;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
