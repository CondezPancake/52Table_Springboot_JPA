package springboot.domain.medicationroute.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.medicationroute.model.aggregate.MedicationRoute;
import springboot.domain.medicationroute.model.valueobject.MedicationRouteId;

public interface MedicationRouteRepository {
    MedicationRoute save(MedicationRoute aggregate);
    Optional<MedicationRoute> findById(MedicationRouteId id);
    List<MedicationRoute> findAll();
    boolean existsByCode(String code);
    void delete(MedicationRoute aggregate);
}
