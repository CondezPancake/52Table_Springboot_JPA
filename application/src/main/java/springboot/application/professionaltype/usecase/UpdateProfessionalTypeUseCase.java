package springboot.application.professionaltype.usecase;

import springboot.application.professionaltype.command.UpdateProfessionalTypeCommand;
import springboot.application.professionaltype.dto.ProfessionalTypeResponse;
import springboot.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import springboot.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class UpdateProfessionalTypeUseCase {
    private final ProfessionalTypeRepository repository;
    public UpdateProfessionalTypeUseCase(ProfessionalTypeRepository repository) { this.repository = repository; }

    public ProfessionalTypeResponse execute(UpdateProfessionalTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ProfessionalTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.name());
        var saved = repository.save(aggregate);
        return new ProfessionalTypeResponse(
                saved.id().value(),
                saved.name(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
