package springboot.domain.treatmentplan.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;
import java.time.LocalDate;

import springboot.domain.common.model.AggregateRoot;
import springboot.domain.treatmentplan.event.TreatmentPlanRegisteredEvent;
import springboot.domain.treatmentplan.event.TreatmentPlanUpdatedEvent;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public class TreatmentPlan extends AggregateRoot {
    private final TreatmentPlanId id;
    private EncounterId encounterId;
    private ProfessionalId professionalId;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private TreatmentStatusId treatmentStatusId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private TreatmentPlan(
            TreatmentPlanId id,
            EncounterId encounterId,
            ProfessionalId professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            TreatmentStatusId treatmentStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.encounterId = Objects.requireNonNull(encounterId, "encounterId must not be null");
        this.professionalId = Objects.requireNonNull(professionalId, "professionalId must not be null");
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.startDate = Objects.requireNonNull(startDate, "startDate must not be null");
        this.endDate = Objects.requireNonNull(endDate, "endDate must not be null");
        this.treatmentStatusId = Objects.requireNonNull(treatmentStatusId, "treatmentStatusId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static TreatmentPlan register(
            EncounterId encounterId,
            ProfessionalId professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            TreatmentStatusId treatmentStatusId) {
        TreatmentPlanId id = TreatmentPlanId.generate();
        LocalDateTime now = LocalDateTime.now();
        TreatmentPlan aggregate = new TreatmentPlan(
                id,
                encounterId,
                professionalId,
                title,
                description,
                startDate,
                endDate,
                treatmentStatusId,
                now,
                now);
        aggregate.recordEvent(new TreatmentPlanRegisteredEvent(id, now));
        return aggregate;
    }

    public static TreatmentPlan restore(
            TreatmentPlanId id,
            EncounterId encounterId,
            ProfessionalId professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            TreatmentStatusId treatmentStatusId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new TreatmentPlan(
                id,
                encounterId,
                professionalId,
                title,
                description,
                startDate,
                endDate,
                treatmentStatusId,
                createdAt,
                updatedAt);
    }

    public void update(
            EncounterId encounterId,
            ProfessionalId professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            TreatmentStatusId treatmentStatusId) {
        this.encounterId = Objects.requireNonNull(encounterId, "encounterId must not be null");
        this.professionalId = Objects.requireNonNull(professionalId, "professionalId must not be null");
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.startDate = Objects.requireNonNull(startDate, "startDate must not be null");
        this.endDate = Objects.requireNonNull(endDate, "endDate must not be null");
        this.treatmentStatusId = Objects.requireNonNull(treatmentStatusId, "treatmentStatusId must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new TreatmentPlanUpdatedEvent(
                        this.id,
                        this.encounterId,
                        this.professionalId,
                        this.title,
                        this.description,
                        this.startDate,
                        this.endDate,
                        this.treatmentStatusId,
                        this.updatedAt));
    }

    public TreatmentPlanId id() {
        return id;
    }

    public EncounterId encounterId() {
        return encounterId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }

    public TreatmentStatusId treatmentStatusId() {
        return treatmentStatusId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
