package springboot.infrastructure.messagetype.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.messagetype.adapters.out.persistence.entity.MessageTypeJpaEntity;

public interface MessageTypeJpaRepository extends JpaRepository<MessageTypeJpaEntity, UUID> {

}
