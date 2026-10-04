package springboot.application.treatmentplan.usecase;

import springboot.application.treatmentplan.dto.TreatmentPlanResponse;
import springboot.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import springboot.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class GetTreatmentPlanByIdUseCase {
    private final TreatmentPlanRepository repository;
    public GetTreatmentPlanByIdUseCase(TreatmentPlanRepository repository) { this.repository = repository; }

    public TreatmentPlanResponse execute(TreatmentPlanId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentPlanNotFoundApplicationException(id.value().toString()));
        return new TreatmentPlanResponse(
                aggregate.id().value(),
                aggregate.encounterId().value(),
                aggregate.professionalId().value(),
                aggregate.title(),
                aggregate.description(),
                aggregate.startDate(),
                aggregate.endDate(),
                aggregate.treatmentStatusId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
