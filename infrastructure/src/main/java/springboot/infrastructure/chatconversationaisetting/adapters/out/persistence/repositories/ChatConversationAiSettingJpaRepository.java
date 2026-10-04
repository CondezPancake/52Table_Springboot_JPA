package springboot.infrastructure.chatconversationaisetting.adapters.out.persistence.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import springboot.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;

public interface ChatConversationAiSettingJpaRepository extends JpaRepository<ChatConversationAiSettingJpaEntity, UUID> {

}
