package springboot.domain.professionalstudy.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;

public interface ProfessionalStudyRepository {
    ProfessionalStudy save(ProfessionalStudy aggregate);
    Optional<ProfessionalStudy> findById(ProfessionalStudyId id);
    List<ProfessionalStudy> findAll();

    void delete(ProfessionalStudy aggregate);
}
