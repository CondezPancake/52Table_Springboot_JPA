package springboot.application.patientallergy.usecase;

import springboot.application.patientallergy.dto.PatientAllergyResponse;
import springboot.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import springboot.domain.patientallergy.model.valueobject.PatientAllergyId;
import springboot.domain.patientallergy.port.repository.PatientAllergyRepository;

public class GetPatientAllergyByIdUseCase {
    private final PatientAllergyRepository repository;
    public GetPatientAllergyByIdUseCase(PatientAllergyRepository repository) { this.repository = repository; }

    public PatientAllergyResponse execute(PatientAllergyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PatientAllergyNotFoundApplicationException(id.value().toString()));
        return new PatientAllergyResponse(
                aggregate.id().value(),
                aggregate.patientId().value(),
                aggregate.substance(),
                aggregate.reaction(),
                aggregate.severity(),
                aggregate.active(),
                aggregate.recordedAt(),
                aggregate.recordedBy().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
