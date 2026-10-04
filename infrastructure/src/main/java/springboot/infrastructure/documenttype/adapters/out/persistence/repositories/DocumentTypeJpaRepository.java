package springboot.infrastructure.documenttype.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.documenttype.adapters.out.persistence.entity.DocumentTypeJpaEntity;

public interface DocumentTypeJpaRepository extends JpaRepository<DocumentTypeJpaEntity, UUID> {
    boolean existsByCode(String code);
}
