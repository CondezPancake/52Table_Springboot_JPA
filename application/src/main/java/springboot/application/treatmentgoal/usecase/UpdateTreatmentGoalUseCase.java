package springboot.application.treatmentgoal.usecase;

import springboot.application.treatmentgoal.command.UpdateTreatmentGoalCommand;
import springboot.application.treatmentgoal.dto.TreatmentGoalResponse;
import springboot.application.treatmentgoal.exception.TreatmentGoalNotFoundApplicationException;
import springboot.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class UpdateTreatmentGoalUseCase {
    private final TreatmentGoalRepository repository;
    public UpdateTreatmentGoalUseCase(TreatmentGoalRepository repository) { this.repository = repository; }

    public TreatmentGoalResponse execute(UpdateTreatmentGoalCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentGoalNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.treatmentPlanId(),
                command.description(),
                command.targetDate(),
                command.completedAt(),
                command.notes(),
                command.treatmentGoalStatusId());
        var saved = repository.save(aggregate);
        return new TreatmentGoalResponse(
                saved.id().value(),
                saved.treatmentPlanId().value(),
                saved.description(),
                saved.targetDate(),
                saved.completedAt(),
                saved.notes(),
                saved.treatmentGoalStatusId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
