package springboot.infrastructure.chatairunerror.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.chatairunerror.adapters.out.persistence.entity.ChatAiRunErrorJpaEntity;

public interface ChatAiRunErrorJpaRepository extends JpaRepository<ChatAiRunErrorJpaEntity, UUID> {

}
