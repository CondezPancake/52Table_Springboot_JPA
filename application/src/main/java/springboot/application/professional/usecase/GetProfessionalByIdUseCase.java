package springboot.application.professional.usecase;

import springboot.application.professional.dto.ProfessionalResponse;
import springboot.application.professional.exception.ProfessionalNotFoundApplicationException;
import springboot.domain.professional.model.valueobject.ProfessionalId;
import springboot.domain.professional.port.repository.ProfessionalRepository;

public class GetProfessionalByIdUseCase {
    private final ProfessionalRepository repository;
    public GetProfessionalByIdUseCase(ProfessionalRepository repository) { this.repository = repository; }

    public ProfessionalResponse execute(ProfessionalId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProfessionalNotFoundApplicationException(id.value().toString()));
        return new ProfessionalResponse(
                aggregate.id().value(),
                aggregate.documentTypeId().value(),
                aggregate.documentNumber(),
                aggregate.firstName(),
                aggregate.lastName(),
                aggregate.professionalTypeId().value(),
                aggregate.licenseNumber(),
                aggregate.active(),
                aggregate.cityId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
