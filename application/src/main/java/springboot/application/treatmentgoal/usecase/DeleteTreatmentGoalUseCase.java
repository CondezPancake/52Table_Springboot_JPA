package springboot.application.treatmentgoal.usecase;

import java.time.LocalDateTime;

import springboot.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import springboot.domain.treatmentgoal.event.TreatmentGoalDeletedEvent;
import springboot.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import springboot.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class DeleteTreatmentGoalUseCase {
    private final TreatmentGoalRepository repository;
    public DeleteTreatmentGoalUseCase(TreatmentGoalRepository repository) { this.repository = repository; }

    public TreatmentGoalDeletedEvent execute(TreatmentGoalId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentGoalNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new TreatmentGoalDeletedEvent(id, LocalDateTime.now());
    }
}
