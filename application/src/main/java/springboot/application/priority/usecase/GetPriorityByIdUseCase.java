package springboot.application.priority.usecase;

import springboot.application.priority.dto.PriorityResponse;
import springboot.application.priority.exception.PriorityNotFoundApplicationException;
import springboot.domain.priority.model.valueobject.PriorityId;
import springboot.domain.priority.port.repository.PriorityRepository;

public class GetPriorityByIdUseCase {
    private final PriorityRepository repository;
    public GetPriorityByIdUseCase(PriorityRepository repository) { this.repository = repository; }

    public PriorityResponse execute(PriorityId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PriorityNotFoundApplicationException(id.value().toString()));
        return new PriorityResponse(
                aggregate.id().value(),
                aggregate.namePriority(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
