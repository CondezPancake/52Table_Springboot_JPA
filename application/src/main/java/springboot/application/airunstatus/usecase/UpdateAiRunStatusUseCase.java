package springboot.application.airunstatus.usecase;

import springboot.application.airunstatus.command.UpdateAiRunStatusCommand;
import springboot.application.airunstatus.dto.AiRunStatusResponse;
import springboot.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import springboot.domain.airunstatus.port.repository.AiRunStatusRepository;

public class UpdateAiRunStatusUseCase {
    private final AiRunStatusRepository repository;
    public UpdateAiRunStatusUseCase(AiRunStatusRepository repository) { this.repository = repository; }

    public AiRunStatusResponse execute(UpdateAiRunStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AiRunStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.nameStatus());
        var saved = repository.save(aggregate);
        return new AiRunStatusResponse(
                saved.id().value(),
                saved.nameStatus(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
