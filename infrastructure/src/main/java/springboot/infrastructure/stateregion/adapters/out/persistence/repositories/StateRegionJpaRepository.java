package springboot.infrastructure.stateregion.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;

public interface StateRegionJpaRepository extends JpaRepository<StateRegionJpaEntity, UUID> {
    boolean existsByCodeRegion(String code);
}
