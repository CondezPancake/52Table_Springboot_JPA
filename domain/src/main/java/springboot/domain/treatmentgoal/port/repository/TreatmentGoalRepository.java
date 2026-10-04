package springboot.domain.treatmentgoal.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import springboot.domain.treatmentgoal.model.valueobject.TreatmentGoalId;

public interface TreatmentGoalRepository {
    TreatmentGoal save(TreatmentGoal aggregate);
    Optional<TreatmentGoal> findById(TreatmentGoalId id);
    List<TreatmentGoal> findAll();

    void delete(TreatmentGoal aggregate);
}
