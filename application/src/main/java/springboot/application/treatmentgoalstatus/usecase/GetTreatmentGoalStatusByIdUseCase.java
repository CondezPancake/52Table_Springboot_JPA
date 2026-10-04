package springboot.application.treatmentgoalstatus.usecase;

import springboot.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import springboot.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import springboot.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import springboot.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class GetTreatmentGoalStatusByIdUseCase {
    private final TreatmentGoalStatusRepository repository;
    public GetTreatmentGoalStatusByIdUseCase(TreatmentGoalStatusRepository repository) { this.repository = repository; }

    public TreatmentGoalStatusResponse execute(TreatmentGoalStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentGoalStatusNotFoundApplicationException(id.value().toString()));
        return new TreatmentGoalStatusResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
