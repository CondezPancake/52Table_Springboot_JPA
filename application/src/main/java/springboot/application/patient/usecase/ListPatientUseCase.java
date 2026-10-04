package springboot.application.patient.usecase;

import java.util.List;

import springboot.application.patient.dto.PatientResponse;
import springboot.domain.patient.port.repository.PatientRepository;

public class ListPatientUseCase {
    private final PatientRepository repository;
    public ListPatientUseCase(PatientRepository repository) { this.repository = repository; }

    public List<PatientResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new PatientResponse(
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
                                aggregate.updatedAt()))
                .toList();
    }
}
