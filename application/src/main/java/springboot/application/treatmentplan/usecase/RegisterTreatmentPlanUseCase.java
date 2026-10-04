package springboot.application.treatmentplan.usecase;

import springboot.application.treatmentplan.command.RegisterTreatmentPlanCommand;
import springboot.application.treatmentplan.dto.TreatmentPlanResponse;
import springboot.domain.treatmentplan.model.aggregate.TreatmentPlan;
import springboot.domain.treatmentplan.port.repository.TreatmentPlanRepository;

public class RegisterTreatmentPlanUseCase {
    private final TreatmentPlanRepository repository;
    public RegisterTreatmentPlanUseCase(TreatmentPlanRepository repository) { this.repository = repository; }

    public TreatmentPlanResponse execute(RegisterTreatmentPlanCommand command) {
        TreatmentPlan aggregate = TreatmentPlan.register(
                command.encounterId(),
                command.professionalId(),
                command.title(),
                command.description(),
                command.startDate(),
                command.endDate(),
                command.treatmentStatusId());
        TreatmentPlan saved = repository.save(aggregate);
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
