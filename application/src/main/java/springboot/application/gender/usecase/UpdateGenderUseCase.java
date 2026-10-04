package springboot.application.gender.usecase;

import springboot.application.gender.command.UpdateGenderCommand;
import springboot.application.gender.dto.GenderResponse;
import springboot.application.gender.exception.GenderNotFoundApplicationException;
import springboot.domain.gender.port.repository.GenderRepository;

public class UpdateGenderUseCase {
    private final GenderRepository repository;
    public UpdateGenderUseCase(GenderRepository repository) { this.repository = repository; }

    public GenderResponse execute(UpdateGenderCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new GenderNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.description());
        var saved = repository.save(aggregate);
        return new GenderResponse(
                saved.id().value(),
                saved.description(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
