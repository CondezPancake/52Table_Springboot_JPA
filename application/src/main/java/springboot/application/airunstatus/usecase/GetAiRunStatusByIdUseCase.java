package springboot.application.airunstatus.usecase;

import springboot.application.airunstatus.dto.AiRunStatusResponse;
import springboot.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import springboot.domain.airunstatus.model.valueobject.AiRunStatusId;
import springboot.domain.airunstatus.port.repository.AiRunStatusRepository;

public class GetAiRunStatusByIdUseCase {
    private final AiRunStatusRepository repository;
    public GetAiRunStatusByIdUseCase(AiRunStatusRepository repository) { this.repository = repository; }

    public AiRunStatusResponse execute(AiRunStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AiRunStatusNotFoundApplicationException(id.value().toString()));
        return new AiRunStatusResponse(
                aggregate.id().value(),
                aggregate.nameStatus(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
