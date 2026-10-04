package springboot.infrastructure.encountertype.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;

public interface EncounterTypeJpaRepository extends JpaRepository<EncounterTypeJpaEntity, UUID> {
    boolean existsByCode(String code);
}
