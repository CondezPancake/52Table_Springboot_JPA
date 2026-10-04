package springboot.infrastructure.professionalstudy.adapters.out.persistence.mappers;

import springboot.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import springboot.domain.country.model.valueobject.CountryId;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.study.model.valueobject.StudyId;
import springboot.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;

public class ProfessionalStudyPersistenceMapper {
    public ProfessionalStudyJpaEntity toJpa(ProfessionalStudy domain) {
        if (domain == null) { return null; }
        ProfessionalStudyJpaEntity jpa = new ProfessionalStudyJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setStudyId(domain.studyId().value());
        jpa.setProfessionalId(domain.professionalId().value());
        jpa.setTitle(domain.title());
        jpa.setUniversity(domain.university());
        jpa.setValid(domain.valid());
        jpa.setResolutionNumber(domain.resolutionNumber());
        jpa.setCountryId(domain.countryId().value());
        jpa.setCreatedAt(domain.createdAt());
        jpa.setUpdatedAt(domain.updatedAt());
        return jpa;
    }

    public ProfessionalStudy toDomain(ProfessionalStudyJpaEntity jpa) {
        if (jpa == null) { return null; }
        return ProfessionalStudy.restore(
                new ProfessionalStudyId(jpa.getId()),
                new StudyId(jpa.getStudyId()),
                new ProfessionalId(jpa.getProfessionalId()),
                jpa.getTitle(),
                jpa.getUniversity(),
                jpa.isValid(),
                jpa.getResolutionNumber(),
                new CountryId(jpa.getCountryId()),
                jpa.getCreatedAt(),
                jpa.getUpdatedAt());
    }
}
