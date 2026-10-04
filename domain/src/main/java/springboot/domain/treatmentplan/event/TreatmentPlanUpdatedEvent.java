package springboot.domain.treatmentplan.event;

import java.time.LocalDateTime;
import java.util.Objects;
import java.time.LocalDate;

import springboot.domain.common.event.DomainEvent;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import springboot.domain.encounter.model.valueobject.EncounterId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public record TreatmentPlanUpdatedEvent(
        TreatmentPlanId id,
        EncounterId encounterId,
        ProfessionalId professionalId,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        TreatmentStatusId treatmentStatusId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public TreatmentPlanUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(encounterId, "encounterId must not be null");
        Objects.requireNonNull(professionalId, "professionalId must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(description, "description must not be null");
        Objects.requireNonNull(startDate, "startDate must not be null");
        Objects.requireNonNull(endDate, "endDate must not be null");
        Objects.requireNonNull(treatmentStatusId, "treatmentStatusId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
