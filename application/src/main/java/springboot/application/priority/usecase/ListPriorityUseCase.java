package springboot.application.priority.usecase;

import java.util.List;

import springboot.application.priority.dto.PriorityResponse;
import springboot.domain.priority.port.repository.PriorityRepository;

public class ListPriorityUseCase {
    private final PriorityRepository repository;
    public ListPriorityUseCase(PriorityRepository repository) { this.repository = repository; }

    public List<PriorityResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new PriorityResponse(
                                aggregate.id().value(),
                                aggregate.namePriority(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
