package springboot.domain.chatairunerror.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatairunerror.model.aggregate.ChatAiRunError;
import springboot.domain.chatairunerror.model.valueobject.ChatAiRunErrorId;

public interface ChatAiRunErrorRepository {
    ChatAiRunError save(ChatAiRunError aggregate);
    Optional<ChatAiRunError> findById(ChatAiRunErrorId id);
    List<ChatAiRunError> findAll();

    void delete(ChatAiRunError aggregate);
}
