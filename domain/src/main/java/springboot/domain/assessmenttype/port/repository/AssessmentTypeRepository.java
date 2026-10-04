package springboot.domain.assessmenttype.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.assessmenttype.model.aggregate.AssessmentType;
import springboot.domain.assessmenttype.model.valueobject.AssessmentTypeId;

public interface AssessmentTypeRepository {
    AssessmentType save(AssessmentType aggregate);
    Optional<AssessmentType> findById(AssessmentTypeId id);
    List<AssessmentType> findAll();
    boolean existsByCode(String code);
    void delete(AssessmentType aggregate);
}
