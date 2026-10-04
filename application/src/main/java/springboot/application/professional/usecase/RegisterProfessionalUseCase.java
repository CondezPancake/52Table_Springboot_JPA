package springboot.application.professional.usecase;

import springboot.application.professional.command.RegisterProfessionalCommand;
import springboot.application.professional.dto.ProfessionalResponse;
import springboot.domain.professional.model.aggregate.Professional;
import springboot.domain.professional.port.repository.ProfessionalRepository;

public class RegisterProfessionalUseCase {
    private final ProfessionalRepository repository;
    public RegisterProfessionalUseCase(ProfessionalRepository repository) { this.repository = repository; }

    public ProfessionalResponse execute(RegisterProfessionalCommand command) {
        Professional aggregate = Professional.register(
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.lastName(),
                command.professionalTypeId(),
                command.licenseNumber(),
                command.active(),
                command.cityId());
        Professional saved = repository.save(aggregate);
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
