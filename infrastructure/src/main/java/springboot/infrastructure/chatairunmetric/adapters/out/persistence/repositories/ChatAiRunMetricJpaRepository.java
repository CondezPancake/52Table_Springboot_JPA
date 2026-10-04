package springboot.infrastructure.chatairunmetric.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;

public interface ChatAiRunMetricJpaRepository extends JpaRepository<ChatAiRunMetricJpaEntity, UUID> {

}
