package springboot.application.treatmentplan.usecase;

import java.time.LocalDateTime;

import springboot.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import springboot.domain.treatmentplan.event.TreatmentPlanDeletedEvent;
import springboot.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import springboot.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class DeleteTreatmentPlanUseCase {
    private final TreatmentPlanRepository repository;
    public DeleteTreatmentPlanUseCase(TreatmentPlanRepository repository) { this.repository = repository; }

    public TreatmentPlanDeletedEvent execute(TreatmentPlanId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new TreatmentPlanNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new TreatmentPlanDeletedEvent(id, LocalDateTime.now());
    }
}
