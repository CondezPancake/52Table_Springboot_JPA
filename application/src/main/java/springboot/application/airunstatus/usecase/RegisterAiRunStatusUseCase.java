package springboot.application.airunstatus.usecase;

import springboot.application.airunstatus.command.RegisterAiRunStatusCommand;
import springboot.application.airunstatus.dto.AiRunStatusResponse;
import springboot.domain.airunstatus.model.aggregate.AiRunStatus;
import springboot.domain.airunstatus.port.repository.AiRunStatusRepository;

public class RegisterAiRunStatusUseCase {
    private final AiRunStatusRepository repository;
    public RegisterAiRunStatusUseCase(AiRunStatusRepository repository) { this.repository = repository; }

    public AiRunStatusResponse execute(RegisterAiRunStatusCommand command) {
        AiRunStatus aggregate = AiRunStatus.register(
                command.nameStatus());
        AiRunStatus saved = repository.save(aggregate);
        return new AiRunStatusResponse(
                saved.id().value(),
                saved.nameStatus(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
