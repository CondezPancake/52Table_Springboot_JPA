package springboot.application.priority.usecase;

import springboot.application.priority.command.UpdatePriorityCommand;
import springboot.application.priority.dto.PriorityResponse;
import springboot.application.priority.exception.PriorityNotFoundApplicationException;
import springboot.domain.priority.port.repository.PriorityRepository;

public class UpdatePriorityUseCase {
    private final PriorityRepository repository;
    public UpdatePriorityUseCase(PriorityRepository repository) { this.repository = repository; }

    public PriorityResponse execute(UpdatePriorityCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new PriorityNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.namePriority());
        var saved = repository.save(aggregate);
        return new PriorityResponse(
                saved.id().value(),
                saved.namePriority(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
