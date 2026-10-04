package springboot.application.professional.usecase;

import springboot.application.professional.command.UpdateProfessionalCommand;
import springboot.application.professional.dto.ProfessionalResponse;
import springboot.application.professional.exception.ProfessionalNotFoundApplicationException;
import springboot.domain.professional.port.repository.ProfessionalRepository;

public class UpdateProfessionalUseCase {
    private final ProfessionalRepository repository;
    public UpdateProfessionalUseCase(ProfessionalRepository repository) { this.repository = repository; }

    public ProfessionalResponse execute(UpdateProfessionalCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ProfessionalNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.lastName(),
                command.professionalTypeId(),
                command.licenseNumber(),
                command.active(),
                command.cityId());
        var saved = repository.save(aggregate);
        return new ProfessionalResponse(
                saved.id().value(),
                saved.documentTypeId().value(),
                saved.documentNumber(),
                saved.firstName(),
                saved.lastName(),
                saved.professionalTypeId().value(),
                saved.licenseNumber(),
                saved.active(),
                saved.cityId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
