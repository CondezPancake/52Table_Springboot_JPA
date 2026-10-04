package springboot.application.treatmentgoalstatus.usecase;

import springboot.application.treatmentgoalstatus.command.UpdateTreatmentGoalStatusCommand;
import springboot.application.treatmentgoalstatus.dto.TreatmentGoalStatusResponse;
import springboot.application.treatmentgoalstatus.exception.TreatmentGoalStatusNotFoundApplicationException;
import springboot.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

public class UpdateTreatmentGoalStatusUseCase {
    private final TreatmentGoalStatusRepository repository;
    public UpdateTreatmentGoalStatusUseCase(TreatmentGoalStatusRepository repository) { this.repository = repository; }

    public TreatmentGoalStatusResponse execute(UpdateTreatmentGoalStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentGoalStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active());
        var saved = repository.save(aggregate);
        return new TreatmentGoalStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
