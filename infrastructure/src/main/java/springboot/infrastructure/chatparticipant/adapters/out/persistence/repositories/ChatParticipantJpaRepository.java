package springboot.infrastructure.chatparticipant.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.chatparticipant.adapters.out.persistence.entity.ChatParticipantJpaEntity;

public interface ChatParticipantJpaRepository extends JpaRepository<ChatParticipantJpaEntity, UUID> {

}
