package springboot.domain.study.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.study.model.aggregate.Study;
import springboot.domain.study.model.valueobject.StudyId;

public interface StudyRepository {
    Study save(Study aggregate);
    Optional<Study> findById(StudyId id);
    List<Study> findAll();

    void delete(Study aggregate);
}
