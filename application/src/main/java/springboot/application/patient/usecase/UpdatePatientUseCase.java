package springboot.application.patient.usecase;

import springboot.application.patient.command.UpdatePatientCommand;
import springboot.application.patient.dto.PatientResponse;
import springboot.application.patient.exception.PatientNotFoundApplicationException;
import springboot.domain.patient.port.repository.PatientRepository;

public class UpdatePatientUseCase {
    private final PatientRepository repository;
    public UpdatePatientUseCase(PatientRepository repository) { this.repository = repository; }

    public PatientResponse execute(UpdatePatientCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new PatientNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.middleName(),
                command.lastName(),
                command.secondLastName(),
                command.birthDate(),
                command.biologicalSexId(),
                command.genderIdentityId(),
                command.email(),
                command.phone(),
                command.address(),
                command.active(),
                command.createdBy(),
                command.updatedBy(),
                command.cityId());
        var saved = repository.save(aggregate);
        return new PatientResponse(
                saved.id().value(),
                saved.documentTypeId().value(),
                saved.documentNumber(),
                saved.firstName(),
                saved.middleName(),
                saved.lastName(),
                saved.secondLastName(),
                saved.birthDate(),
                saved.biologicalSexId().value(),
                saved.genderIdentityId().value(),
                saved.email(),
                saved.phone(),
                saved.address(),
                saved.active(),
                saved.createdBy() == null ? null : saved.createdBy().value(),
                saved.updatedBy() == null ? null : saved.updatedBy().value(),
                saved.cityId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
