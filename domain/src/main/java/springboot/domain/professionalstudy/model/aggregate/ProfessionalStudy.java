package springboot.domain.professionalstudy.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.professionalstudy.event.ProfessionalStudyRegisteredEvent;
import springboot.domain.professionalstudy.event.ProfessionalStudyUpdatedEvent;
import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.study.model.valueobject.StudyId;

public class ProfessionalStudy extends AggregateRoot {
    private final ProfessionalStudyId id;
    private StudyId studyId;
    private ProfessionalId professionalId;
    private String title;
    private String university;
    private boolean valid;
    private String resolutionNumber;
    private CountryId countryId;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ProfessionalStudy(
            ProfessionalStudyId id,
            StudyId studyId,
            ProfessionalId professionalId,
            String title,
            String university,
            boolean valid,
            String resolutionNumber,
            CountryId countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.studyId = Objects.requireNonNull(studyId, "studyId must not be null");
        this.professionalId = Objects.requireNonNull(professionalId, "professionalId must not be null");
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.university = Objects.requireNonNull(university, "university must not be null");
        this.valid = valid;
        this.resolutionNumber = resolutionNumber;
        this.countryId = Objects.requireNonNull(countryId, "countryId must not be null");
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt must not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt must not be null");
    }

    public static ProfessionalStudy register(
            StudyId studyId,
            ProfessionalId professionalId,
            String title,
            String university,
            boolean valid,
            String resolutionNumber,
            CountryId countryId) {
        ProfessionalStudyId id = ProfessionalStudyId.generate();
        LocalDateTime now = LocalDateTime.now();
        ProfessionalStudy aggregate = new ProfessionalStudy(
                id,
                studyId,
                professionalId,
                title,
                university,
                valid,
                resolutionNumber,
                countryId,
                now,
                now);
        aggregate.recordEvent(new ProfessionalStudyRegisteredEvent(id, now));
        return aggregate;
    }

    public static ProfessionalStudy restore(
            ProfessionalStudyId id,
            StudyId studyId,
            ProfessionalId professionalId,
            String title,
            String university,
            boolean valid,
            String resolutionNumber,
            CountryId countryId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {
        return new ProfessionalStudy(
                id,
                studyId,
                professionalId,
                title,
                university,
                valid,
                resolutionNumber,
                countryId,
                createdAt,
                updatedAt);
    }

    public void update(
            StudyId studyId,
            ProfessionalId professionalId,
            String title,
            String university,
            boolean valid,
            String resolutionNumber,
            CountryId countryId) {
        this.studyId = Objects.requireNonNull(studyId, "studyId must not be null");
        this.professionalId = Objects.requireNonNull(professionalId, "professionalId must not be null");
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.university = Objects.requireNonNull(university, "university must not be null");
        this.valid = valid;
        this.resolutionNumber = resolutionNumber;
        this.countryId = Objects.requireNonNull(countryId, "countryId must not be null");
        this.updatedAt = LocalDateTime.now();
        recordEvent(new ProfessionalStudyUpdatedEvent(
                        this.id,
                        this.studyId,
                        this.professionalId,
                        this.title,
                        this.university,
                        this.valid,
                        this.resolutionNumber,
                        this.countryId,
                        this.updatedAt));
    }

    public ProfessionalStudyId id() {
        return id;
    }

    public StudyId studyId() {
        return studyId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }

    public String title() {
        return title;
    }

    public String university() {
        return university;
    }

    public boolean valid() {
        return valid;
    }

    public String resolutionNumber() {
        return resolutionNumber;
    }

    public CountryId countryId() {
        return countryId;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
