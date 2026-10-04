package springboot.application.gender.usecase;

import springboot.application.gender.command.RegisterGenderCommand;
import springboot.application.gender.dto.GenderResponse;
import springboot.domain.gender.model.aggregate.Gender;
import springboot.domain.gender.port.repository.GenderRepository;

public class RegisterGenderUseCase {
    private final GenderRepository repository;
    public RegisterGenderUseCase(GenderRepository repository) { this.repository = repository; }

    public GenderResponse execute(RegisterGenderCommand command) {
        Gender aggregate = Gender.register(
                command.description());
        Gender saved = repository.save(aggregate);
        return new GenderResponse(
                saved.id().value(),
                saved.description(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
