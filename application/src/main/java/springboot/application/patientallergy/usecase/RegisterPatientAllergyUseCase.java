package springboot.application.patientallergy.usecase;

import springboot.application.patientallergy.command.RegisterPatientAllergyCommand;
import springboot.application.patientallergy.dto.PatientAllergyResponse;
import springboot.domain.patientallergy.model.aggregate.PatientAllergy;
import springboot.domain.patientallergy.port.repository.PatientAllergyRepository;

public class RegisterPatientAllergyUseCase {
    private final PatientAllergyRepository repository;
    public RegisterPatientAllergyUseCase(PatientAllergyRepository repository) { this.repository = repository; }

    public PatientAllergyResponse execute(RegisterPatientAllergyCommand command) {
        PatientAllergy aggregate = PatientAllergy.register(
                command.patientId(),
                command.substance(),
                command.reaction(),
                command.severity(),
                command.active(),
                command.recordedAt(),
                command.recordedBy());
        PatientAllergy saved = repository.save(aggregate);
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
