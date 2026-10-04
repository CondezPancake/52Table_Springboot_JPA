package springboot.application.aimodel.usecase;

import springboot.application.aimodel.dto.AiModelResponse;
import springboot.application.aimodel.exception.AiModelNotFoundApplicationException;
import springboot.domain.aimodel.model.valueobject.AiModelId;
import springboot.domain.aimodel.port.repository.AiModelRepository;

public class GetAiModelByIdUseCase {
    private final AiModelRepository repository;
    public GetAiModelByIdUseCase(AiModelRepository repository) { this.repository = repository; }

    public AiModelResponse execute(AiModelId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AiModelNotFoundApplicationException(id.value().toString()));
        return new AiModelResponse(
                aggregate.id().value(),
                aggregate.providerModelId().value(),
                aggregate.nameModel(),
                aggregate.modelKey(),
                aggregate.inputTokenPrice(),
                aggregate.outputTokenPrice(),
                aggregate.maxTokens(),
                aggregate.contextWindow(),
                aggregate.active(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
