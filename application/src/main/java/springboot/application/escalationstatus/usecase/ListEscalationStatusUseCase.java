package springboot.application.escalationstatus.usecase;

import java.util.List;

import springboot.application.escalationstatus.dto.EscalationStatusResponse;
import springboot.domain.escalationstatus.port.repository.EscalationStatusRepository;

public class ListEscalationStatusUseCase {
    private final EscalationStatusRepository repository;
    public ListEscalationStatusUseCase(EscalationStatusRepository repository) { this.repository = repository; }

    public List<EscalationStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new EscalationStatusResponse(
                                aggregate.id().value(),
                                aggregate.nameStatus(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
