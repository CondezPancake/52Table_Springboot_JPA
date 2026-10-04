package springboot.application.patientallergy.usecase;

import springboot.application.patientallergy.command.UpdatePatientAllergyCommand;
import springboot.application.patientallergy.dto.PatientAllergyResponse;
import springboot.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import springboot.domain.patientallergy.port.repository.PatientAllergyRepository;

public class UpdatePatientAllergyUseCase {
    private final PatientAllergyRepository repository;
    public UpdatePatientAllergyUseCase(PatientAllergyRepository repository) { this.repository = repository; }

    public PatientAllergyResponse execute(UpdatePatientAllergyCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new PatientAllergyNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.patientId(),
                command.substance(),
                command.reaction(),
                command.severity(),
                command.active(),
                command.recordedAt(),
                command.recordedBy());
        var saved = repository.save(aggregate);
        return new PatientAllergyResponse(
                saved.id().value(),
                saved.patientId().value(),
                saved.substance(),
                saved.reaction(),
                saved.severity(),
                saved.active(),
                saved.recordedAt(),
                saved.recordedBy().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
