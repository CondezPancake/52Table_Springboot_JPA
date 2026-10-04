package springboot.application.medicationroute.usecase;

import java.time.LocalDateTime;

import springboot.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import springboot.domain.medicationroute.event.MedicationRouteDeletedEvent;
import springboot.domain.medicationroute.model.valueobject.MedicationRouteId;
import springboot.domain.medicationroute.port.repository.MedicationRouteRepository;

public class DeleteMedicationRouteUseCase {
    private final MedicationRouteRepository repository;
    public DeleteMedicationRouteUseCase(MedicationRouteRepository repository) { this.repository = repository; }

    public MedicationRouteDeletedEvent execute(MedicationRouteId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new MedicationRouteNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new MedicationRouteDeletedEvent(id, LocalDateTime.now());
    }
}
