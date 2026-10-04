package springboot.application.medicationroute.usecase;

import springboot.application.medicationroute.command.UpdateMedicationRouteCommand;
import springboot.application.medicationroute.dto.MedicationRouteResponse;
import springboot.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import springboot.domain.medicationroute.port.repository.MedicationRouteRepository;

public class UpdateMedicationRouteUseCase {
    private final MedicationRouteRepository repository;
    public UpdateMedicationRouteUseCase(MedicationRouteRepository repository) { this.repository = repository; }

    public MedicationRouteResponse execute(UpdateMedicationRouteCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new MedicationRouteNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active());
        var saved = repository.save(aggregate);
        return new MedicationRouteResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
