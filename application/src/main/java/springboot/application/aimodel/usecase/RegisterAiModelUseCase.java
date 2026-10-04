package springboot.application.aimodel.usecase;

import springboot.application.aimodel.command.RegisterAiModelCommand;
import springboot.application.aimodel.dto.AiModelResponse;
import springboot.domain.aimodel.model.aggregate.AiModel;
import springboot.domain.aimodel.port.repository.AiModelRepository;

public class RegisterAiModelUseCase {
    private final AiModelRepository repository;
    public RegisterAiModelUseCase(AiModelRepository repository) { this.repository = repository; }

    public AiModelResponse execute(RegisterAiModelCommand command) {
        AiModel aggregate = AiModel.register(
                command.providerModelId(),
                command.nameModel(),
                command.modelKey(),
                command.inputTokenPrice(),
                command.outputTokenPrice(),
                command.maxTokens(),
                command.contextWindow(),
                command.active());
        AiModel saved = repository.save(aggregate);
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
