package springboot.application.professionaltype.usecase;

import springboot.application.professionaltype.command.RegisterProfessionalTypeCommand;
import springboot.application.professionaltype.dto.ProfessionalTypeResponse;
import springboot.domain.professionaltype.model.aggregate.ProfessionalType;
import springboot.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class RegisterProfessionalTypeUseCase {
    private final ProfessionalTypeRepository repository;
    public RegisterProfessionalTypeUseCase(ProfessionalTypeRepository repository) { this.repository = repository; }

    public ProfessionalTypeResponse execute(RegisterProfessionalTypeCommand command) {
        ProfessionalType aggregate = ProfessionalType.register(
                command.name());
        ProfessionalType saved = repository.save(aggregate);
        return new ProfessionalTypeResponse(
                saved.id().value(),
                saved.name(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
