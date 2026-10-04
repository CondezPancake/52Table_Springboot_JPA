package springboot.infrastructure.consenttype.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;

public interface ConsentTypeJpaRepository extends JpaRepository<ConsentTypeJpaEntity, UUID> {
    boolean existsByCode(String code);
}
