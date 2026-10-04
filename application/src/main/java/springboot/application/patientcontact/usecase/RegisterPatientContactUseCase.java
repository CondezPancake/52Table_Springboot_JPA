package springboot.application.patientcontact.usecase;

import springboot.application.patientcontact.command.RegisterPatientContactCommand;
import springboot.application.patientcontact.dto.PatientContactResponse;
import springboot.domain.patientcontact.model.aggregate.PatientContact;
import springboot.domain.patientcontact.port.repository.PatientContactRepository;

public class RegisterPatientContactUseCase {
    private final PatientContactRepository repository;
    public RegisterPatientContactUseCase(PatientContactRepository repository) { this.repository = repository; }

    public PatientContactResponse execute(RegisterPatientContactCommand command) {
        PatientContact aggregate = PatientContact.register(
                command.contactId(),
                command.patientId(),
                command.primaryContact(),
                command.emergencyContact(),
                command.relationshipTypeId());
        PatientContact saved = repository.save(aggregate);
        return new PatientContactResponse(
                saved.id().value(),
                saved.contactId().value(),
                saved.patientId().value(),
                saved.primaryContact(),
                saved.emergencyContact(),
                saved.relationshipTypeId().value());
    }
}
