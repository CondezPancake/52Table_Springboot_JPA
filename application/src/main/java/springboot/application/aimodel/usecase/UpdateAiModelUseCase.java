package springboot.application.aimodel.usecase;

import springboot.application.aimodel.command.UpdateAiModelCommand;
import springboot.application.aimodel.dto.AiModelResponse;
import springboot.application.aimodel.exception.AiModelNotFoundApplicationException;
import springboot.domain.aimodel.port.repository.AiModelRepository;

public class UpdateAiModelUseCase {
    private final AiModelRepository repository;
    public UpdateAiModelUseCase(AiModelRepository repository) { this.repository = repository; }

    public AiModelResponse execute(UpdateAiModelCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AiModelNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.providerModelId(),
                command.nameModel(),
                command.modelKey(),
                command.inputTokenPrice(),
                command.outputTokenPrice(),
                command.maxTokens(),
                command.contextWindow(),
                command.active());
        var saved = repository.save(aggregate);
        return new AiModelResponse(
                saved.id().value(),
                saved.providerModelId().value(),
                saved.nameModel(),
                saved.modelKey(),
                saved.inputTokenPrice(),
                saved.outputTokenPrice(),
                saved.maxTokens(),
                saved.contextWindow(),
                saved.active(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
