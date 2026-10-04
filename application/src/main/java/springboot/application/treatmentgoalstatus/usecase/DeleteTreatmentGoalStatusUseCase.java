package springboot.application.treatmentgoalstatus.usecase;

import java.time.LocalDateTime;

import springboot.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import springboot.domain.treatmentgoalstatus.event.TreatmentGoalStatusDeletedEvent;
import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import springboot.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class DeleteTreatmentGoalStatusUseCase {
    private final TreatmentGoalStatusRepository repository;
    public DeleteTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) { this.repository = repository; }

    public TreatmentGoalStatusDeletedEvent execute(TreatmentGoalStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentGoalStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new TreatmentGoalStatusDeletedEvent(id, LocalDateTime.now());
    }
}
