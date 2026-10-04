package springboot.application.treatmentgoalstatus.usecase;

import springboot.application.treatmentgoalstatus.command.RegisterTreatmentGoalStatusCommand;
import springboot.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import springboot.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import springboot.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class RegisterTreatmentGoalStatusUseCase {
    private final TreatmentGoalStatusRepository repository;
    public RegisterTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) { this.repository = repository; }

    public TreatmentGoalStatusResponse execute(RegisterTreatmentGoalStatusCommand command) {
        TreatmentGoalStatus aggregate = TreatmentGoalStatus.register(
                command.code(),
                command.name(),
                command.active());
        TreatmentGoalStatus saved = repository.save(aggregate);
        return new TreatmentGoalStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
