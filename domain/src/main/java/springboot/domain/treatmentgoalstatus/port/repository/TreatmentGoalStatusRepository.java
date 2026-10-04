package springboot.domain.treatmentgoalstatus.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public interface TreatmentGoalStatusRepository {
    TreatmentGoalStatus save(TreatmentGoalStatus aggregate);
    Optional<TreatmentGoalStatus> findById(TreatmentGoalStatusId id);
    List<TreatmentGoalStatus> findAll();
    boolean existsByCode(String code);
    void delete(TreatmentGoalStatus aggregate);
}
