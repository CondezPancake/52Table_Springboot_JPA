package springboot.application.medicationroute.usecase;

import springboot.application.medicationroute.dto.MedicationRouteResponse;
import springboot.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import springboot.domain.medicationroute.model.valueobject.MedicationRouteId;
import springboot.domain.medicationroute.port.repository.MedicationRouteRepository;

public class GetMedicationRouteByIdUseCase {
    private final MedicationRouteRepository repository;
    public GetMedicationRouteByIdUseCase(MedicationRouteRepository repository) { this.repository = repository; }

    public MedicationRouteResponse execute(MedicationRouteId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MedicationRouteNotFoundApplicationException(id.value().toString()));
        return new MedicationRouteResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
