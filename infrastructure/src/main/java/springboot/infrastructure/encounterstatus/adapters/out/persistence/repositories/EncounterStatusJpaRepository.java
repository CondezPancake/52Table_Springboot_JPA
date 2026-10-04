package springboot.infrastructure.encounterstatus.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;

public interface EncounterStatusJpaRepository extends JpaRepository<EncounterStatusJpaEntity, UUID> {
    boolean existsByCode(String code);
}
