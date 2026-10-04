package springboot.application.treatmentstatus.usecase;

import springboot.application.treatmentstatus.command.RegisterTreatmentStatusCommand;
import springboot.application.treatmentstatus.dto.TreatmentStatusResponse;
import springboot.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import springboot.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

public class RegisterTreatmentStatusUseCase {
    private final TreatmentStatusRepository repository;
    public RegisterTreatmentStatusUseCase(TreatmentStatusRepository repository) { this.repository = repository; }

    public TreatmentStatusResponse execute(RegisterTreatmentStatusCommand command) {
        TreatmentStatus aggregate = TreatmentStatus.register(
                command.code(),
                command.name(),
                command.active());
        TreatmentStatus saved = repository.save(aggregate);
        return new TreatmentStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
