package springboot.infrastructure.encountermodality.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;

public interface EncounterModalityJpaRepository extends JpaRepository<EncounterModalityJpaEntity, UUID> {
    boolean existsByCode(String code);
}
