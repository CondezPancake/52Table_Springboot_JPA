package springboot.application.treatmentgoal.usecase;

import springboot.application.treatmentgoal.command.RegisterTreatmentGoalCommand;
import springboot.application.treatmentgoal.dto.TreatmentGoalResponse;
import springboot.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import springboot.domain.treatmentgoal.port.repository.TreatmentGoalRepository;

public class RegisterTreatmentGoalUseCase {
    private final TreatmentGoalRepository repository;
    public RegisterTreatmentGoalUseCase(TreatmentGoalRepository repository) { this.repository = repository; }

    public TreatmentGoalResponse execute(RegisterTreatmentGoalCommand command) {
        TreatmentGoal aggregate = TreatmentGoal.register(
                command.treatmentPlanId(),
                command.description(),
                command.targetDate(),
                command.completedAt(),
                command.notes(),
                command.treatmentGoalStatusId());
        TreatmentGoal saved = repository.save(aggregate);
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
