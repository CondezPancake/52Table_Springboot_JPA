package springboot.domain.patientallergy.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.patientallergy.event.PatientAllergyRegisteredEvent;
import springboot.domain.patientallergy.event.PatientAllergyUpdatedEvent;
import springboot.domain.patientallergy.model.valueobject.PatientAllergyId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.professional.model.valueobject.ProfessionalId;

public class PatientAllergy extends AggregateRoot {
    private final PatientAllergyId id;
    private PatientId patientId;
    private String substance;
    private String reaction;
    private String severity;
    private boolean active;
    private LocalDateTime recordedAt;
    private ProfessionalId recordedBy;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private PatientAllergy(
            PatientAllergyId id,
            PatientId patientId,
            String substance,
            String reaction,
            String severity,
            boolean active,
            LocalDateTime recordedAt,
            ProfessionalId recordedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.patientId = Objects.requireNonNull(patientId, "patientId must not be null");
        this.substance = Objects.requireNonNull(substance, "substance must not be null");
        this.reaction = reaction;
        this.severity = Objects.requireNonNull(severity, "severity must not be null");
        this.active = active;
        this.recordedAt = Objects.requireNonNull(recordedAt, "recordedAt must not be null");
        this.recordedBy = Objects.requireNonNull(recordedBy, "recordedBy must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static PatientAllergy register(
            PatientId patientId,
            String substance,
            String reaction,
            String severity,
            boolean active,
            LocalDateTime recordedAt,
            ProfessionalId recordedBy) {
        PatientAllergyId id = PatientAllergyId.generate();
        LocalDateTime now = LocalDateTime.now();
        PatientAllergy aggregate = new PatientAllergy(
                id,
                patientId,
                substance,
                reaction,
                severity,
                active,
                recordedAt,
                recordedBy,
                now,
                now);
        aggregate.recordEvent(new PatientAllergyRegisteredEvent(id, now));
        return aggregate;
    }

    public static PatientAllergy restore(
            PatientAllergyId id,
            PatientId patientId,
            String substance,
            String reaction,
            String severity,
            boolean active,
            LocalDateTime recordedAt,
            ProfessionalId recordedBy,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new PatientAllergy(
                id,
                patientId,
                substance,
                reaction,
                severity,
                active,
                recordedAt,
                recordedBy,
                createdAt,
                updatedAt);
    }

    public void update(
            PatientId patientId,
            String substance,
            String reaction,
            String severity,
            boolean active,
            LocalDateTime recordedAt,
            ProfessionalId recordedBy) {
        this.patientId = Objects.requireNonNull(patientId, "patientId must not be null");
        this.substance = Objects.requireNonNull(substance, "substance must not be null");
        this.reaction = reaction;
        this.severity = Objects.requireNonNull(severity, "severity must not be null");
        this.active = active;
        this.recordedAt = Objects.requireNonNull(recordedAt, "recordedAt must not be null");
        this.recordedBy = Objects.requireNonNull(recordedBy, "recordedBy must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new PatientAllergyUpdatedEvent(
                        this.id,
                        this.patientId,
                        this.substance,
                        this.reaction,
                        this.severity,
                        this.active,
                        this.recordedAt,
                        this.recordedBy,
                        this.updatedAt));
    }

    public PatientAllergyId id() {
        return id;
    }

    public PatientId patientId() {
        return patientId;
    }

    public String substance() {
        return substance;
    }

    public String reaction() {
        return reaction;
    }

    public String severity() {
        return severity;
    }

    public boolean active() {
        return active;
    }

    public LocalDateTime recordedAt() {
        return recordedAt;
    }

    public ProfessionalId recordedBy() {
        return recordedBy;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
