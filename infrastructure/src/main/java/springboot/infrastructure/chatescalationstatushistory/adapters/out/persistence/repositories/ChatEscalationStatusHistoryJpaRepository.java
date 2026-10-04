package springboot.infrastructure.chatescalationstatushistory.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.chatescalationstatushistory.adapters.out.persistence.entity.ChatEscalationStatusHistoryJpaEntity;

public interface ChatEscalationStatusHistoryJpaRepository extends JpaRepository<ChatEscalationStatusHistoryJpaEntity, UUID> {

}
