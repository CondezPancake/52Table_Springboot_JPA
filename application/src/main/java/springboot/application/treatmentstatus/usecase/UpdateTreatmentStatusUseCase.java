package springboot.application.treatmentstatus.usecase;

import springboot.application.treatmentstatus.command.UpdateTreatmentStatusCommand;
import springboot.application.treatmentstatus.dto.TreatmentStatusResponse;
import springboot.application.treatmentstatus.exception.TreatmentStatusNotFoundApplicationException;
import springboot.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class UpdateTreatmentStatusUseCase {
    private final TreatmentStatusRepository repository;
    public UpdateTreatmentStatusUseCase(TreatmentStatusRepository repository) { this.repository = repository; }

    public TreatmentStatusResponse execute(UpdateTreatmentStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new TreatmentStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active());
        var saved = repository.save(aggregate);
        return new TreatmentStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
