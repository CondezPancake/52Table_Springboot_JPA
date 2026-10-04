package springboot.domain.chatairun.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.chatairun.model.aggregate.ChatAiRun;
import springboot.domain.chatairun.model.valueobject.ChatAiRunId;

public interface ChatAiRunRepository {
    ChatAiRun save(ChatAiRun aggregate);
    Optional<ChatAiRun> findById(ChatAiRunId id);
    List<ChatAiRun> findAll();

    void delete(ChatAiRun aggregate);
}
