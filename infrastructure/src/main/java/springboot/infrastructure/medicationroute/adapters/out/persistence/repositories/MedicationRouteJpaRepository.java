package springboot.infrastructure.medicationroute.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.medicationroute.adapters.out.persistence.entity.MedicationRouteJpaEntity;

public interface MedicationRouteJpaRepository extends JpaRepository<MedicationRouteJpaEntity, UUID> {
    boolean existsByCode(String code);
}
