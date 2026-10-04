package springboot.domain.chatparticipant.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatparticipant.model.aggregate.ChatParticipant;
import springboot.domain.chatparticipant.model.valueobject.ChatParticipantId;

public interface ChatParticipantRepository {
    ChatParticipant save(ChatParticipant aggregate);
    Optional<ChatParticipant> findById(ChatParticipantId id);
    List<ChatParticipant> findAll();

    void delete(ChatParticipant aggregate);
}
