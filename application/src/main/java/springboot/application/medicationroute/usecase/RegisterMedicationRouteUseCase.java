package springboot.application.medicationroute.usecase;

import springboot.application.medicationroute.command.RegisterMedicationRouteCommand;
import springboot.application.medicationroute.dto.MedicationRouteResponse;
import springboot.domain.medicationroute.model.aggregate.MedicationRoute;
import springboot.domain.medicationroute.port.repository.MedicationRouteRepository;

public class RegisterMedicationRouteUseCase {
    private final MedicationRouteRepository repository;
    public RegisterMedicationRouteUseCase(MedicationRouteRepository repository) { this.repository = repository; }

    public MedicationRouteResponse execute(RegisterMedicationRouteCommand command) {
        MedicationRoute aggregate = MedicationRoute.register(
                command.code(),
                command.name(),
                command.active());
        MedicationRoute saved = repository.save(aggregate);
        return new MedicationRouteResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
