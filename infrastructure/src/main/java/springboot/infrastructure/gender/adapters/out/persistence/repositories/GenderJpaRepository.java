package springboot.infrastructure.gender.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.gender.adapters.out.persistence.entity.GenderJpaEntity;

public interface GenderJpaRepository extends JpaRepository<GenderJpaEntity, UUID> {

}
