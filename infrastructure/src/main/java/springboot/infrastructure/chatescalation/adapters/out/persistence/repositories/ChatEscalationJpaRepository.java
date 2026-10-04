package springboot.infrastructure.chatescalation.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.chatescalation.adapters.out.persistence.entity.ChatEscalationJpaEntity;

public interface ChatEscalationJpaRepository extends JpaRepository<ChatEscalationJpaEntity, UUID> {

}
