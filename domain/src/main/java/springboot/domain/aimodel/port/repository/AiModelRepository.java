package springboot.domain.aimodel.port.repository;

import java.util.List;
import java.util.Optional;

import springboot.domain.aimodel.model.aggregate.AiModel;
import springboot.domain.aimodel.model.valueobject.AiModelId;

public interface AiModelRepository {
    AiModel save(AiModel aggregate);
    Optional<AiModel> findById(AiModelId id);
    List<AiModel> findAll();

    void delete(AiModel aggregate);
}
