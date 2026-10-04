package springboot.application.priority.usecase;

import springboot.application.priority.command.RegisterPriorityCommand;
import springboot.application.priority.dto.PriorityResponse;
import springboot.domain.priority.model.aggregate.Priority;
import springboot.domain.priority.port.repository.PriorityRepository;

public class RegisterPriorityUseCase {
    private final PriorityRepository repository;
    public RegisterPriorityUseCase(PriorityRepository repository) { this.repository = repository; }

    public PriorityResponse execute(RegisterPriorityCommand command) {
        Priority aggregate = Priority.register(
                command.namePriority());
        Priority saved = repository.save(aggregate);
        return new PriorityResponse(
                saved.id().value(),
                saved.namePriority(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
