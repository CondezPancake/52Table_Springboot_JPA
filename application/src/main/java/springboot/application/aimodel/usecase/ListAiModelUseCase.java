package springboot.application.aimodel.usecase;

import java.util.List;

import springboot.application.aimodel.dto.AiModelResponse;
import springboot.domain.aimodel.port.repository.AiModelRepository;

public class ListAiModelUseCase {
    private final AiModelRepository repository;
    public ListAiModelUseCase(AiModelRepository repository) { this.repository = repository; }

    public List<AiModelResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new AiModelResponse(
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
                                aggregate.updatedAt()))
                .toList();
    }
}
