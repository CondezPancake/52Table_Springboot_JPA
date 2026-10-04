package springboot.application.patient.usecase;

import springboot.application.patient.dto.PatientResponse;
import springboot.application.patient.exception.PatientNotFoundApplicationException;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.patient.port.repository.PatientRepository;

public class GetPatientByIdUseCase {
    private final PatientRepository repository;
    public GetPatientByIdUseCase(PatientRepository repository) { this.repository = repository; }

    public PatientResponse execute(PatientId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PatientNotFoundApplicationException(id.value().toString()));
        return new PatientResponse(
                aggregate.id().value(),
                aggregate.documentTypeId().value(),
                aggregate.documentNumber(),
                aggregate.firstName(),
                aggregate.middleName(),
                aggregate.lastName(),
                aggregate.secondLastName(),
                aggregate.birthDate(),
                aggregate.biologicalSexId().value(),
                aggregate.genderIdentityId().value(),
                aggregate.email(),
                aggregate.phone(),
                aggregate.address(),
                aggregate.active(),
                aggregate.createdBy() == null ? null : aggregate.createdBy().value(),
                aggregate.updatedBy() == null ? null : aggregate.updatedBy().value(),
                aggregate.cityId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
