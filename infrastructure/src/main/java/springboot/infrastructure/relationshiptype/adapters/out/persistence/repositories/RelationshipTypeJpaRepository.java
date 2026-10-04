package springboot.infrastructure.relationshiptype.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;

public interface RelationshipTypeJpaRepository extends JpaRepository<RelationshipTypeJpaEntity, UUID> {

}
