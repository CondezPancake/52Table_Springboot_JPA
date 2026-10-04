package springboot.application.airunstatus.usecase;

import java.util.List;

import springboot.application.airunstatus.dto.AiRunStatusResponse;
import springboot.domain.airunstatus.port.repository.AiRunStatusRepository;

public class ListAiRunStatusUseCase {
    private final AiRunStatusRepository repository;
    public ListAiRunStatusUseCase(AiRunStatusRepository repository) { this.repository = repository; }

    public List<AiRunStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new AiRunStatusResponse(
                                aggregate.id().value(),
                                aggregate.nameStatus(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
