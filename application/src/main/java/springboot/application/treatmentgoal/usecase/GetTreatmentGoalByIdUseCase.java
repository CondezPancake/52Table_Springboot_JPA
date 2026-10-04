package springboot.application.treatmentgoal.usecase;

import springboot.application.treatmentgoal.dto.TreatmentGoalResponse;
import springboot.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import springboot.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import springboot.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class GetTreatmentGoalByIdUseCase {
    private final TreatmentGoalRepository repository;
    public GetTreatmentGoalByIdUseCase(TreatmentGoalRepository repository) { this.repository = repository; }

    public TreatmentGoalResponse execute(TreatmentGoalId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentGoalNotFoundApplicationException(id.value().toString()));
        return new TreatmentGoalResponse(
                aggregate.id().value(),
                aggregate.treatmentPlanId().value(),
                aggregate.description(),
                aggregate.targetDate(),
                aggregate.completedAt(),
                aggregate.notes(),
                aggregate.treatmentGoalStatusId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
