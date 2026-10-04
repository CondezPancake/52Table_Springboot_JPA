package springboot.application.treatmentplan.usecase;

import springboot.application.treatmentplan.command.UpdateTreatmentPlanCommand;
import springboot.application.treatmentplan.dto.TreatmentPlanResponse;
import springboot.application.treatmentplan.exception.TreatmentPlanNotFoundApplicationException;
import springboot.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class UpdateTreatmentPlanUseCase {
    private final TreatmentPlanRepository repository;
    public UpdateTreatmentPlanUseCase(TreatmentPlanRepository repository) { this.repository = repository; }

    public TreatmentPlanResponse execute(UpdateTreatmentPlanCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentPlanNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.encounterId(),
                command.professionalId(),
                command.title(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.treatmentStatusId());
        var saved = repository.save(aggregate);
        return new TreatmentPlanResponse(
                saved.id().value(),
                saved.encounterId().value(),
                saved.professionalId().value(),
                saved.title(),
                saved.description(),
                saved.startDate(),
                saved.endDate(),
                saved.treatmentStatusId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
