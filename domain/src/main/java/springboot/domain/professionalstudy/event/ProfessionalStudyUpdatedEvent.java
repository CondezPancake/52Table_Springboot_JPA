package springboot.domain.professionalstudy.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.study.model.valueobject.StudyId;

public record ProfessionalStudyUpdatedEvent(
        ProfessionalStudyId id,
        StudyId studyId,
        ProfessionalId professionalId,
        String title,
        String university,
        boolean valid,
        String resolutionNumber,
        CountryId countryId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public ProfessionalStudyUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(studyId, "studyId must not be null");
        Objects.requireNonNull(professionalId, "professionalId must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(university, "university must not be null");
        Objects.requireNonNull(countryId, "countryId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
