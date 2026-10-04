package springboot.application.professionalstudy.command;

import java.util.Objects;


import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.study.model.valueobject.StudyId;

public record UpdateProfessionalStudyCommand(
        ProfessionalStudyId id,
        StudyId studyId,
        ProfessionalId professionalId,
        String title,
        String university,
        boolean valid,
        String resolutionNumber,
        CountryId countryId
) {
    public UpdateProfessionalStudyCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(studyId, "studyId must not be null");
        Objects.requireNonNull(professionalId, "professionalId must not be null");
        Objects.requireNonNull(title, "title must not be null");
        Objects.requireNonNull(university, "university must not be null");
        Objects.requireNonNull(countryId, "countryId must not be null");
    }
}
