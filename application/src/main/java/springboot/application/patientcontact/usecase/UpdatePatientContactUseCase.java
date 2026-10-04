package springboot.application.patientcontact.usecase;

import springboot.application.patientcontact.command.UpdatePatientContactCommand;
import springboot.application.patientcontact.dto.PatientContactResponse;
import springboot.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import springboot.domain.patientcontact.port.repository.PatientContactRepository;

public class UpdatePatientContactUseCase {
    private final PatientContactRepository repository;
    public UpdatePatientContactUseCase(PatientContactRepository repository) { this.repository = repository; }

    public PatientContactResponse execute(UpdatePatientContactCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new PatientContactNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.contactId(),
                command.patientId(),
                command.primaryContact(),
                command.emergencyContact(),
                command.relationshipTypeId());
        var saved = repository.save(aggregate);
        return new PatientContactResponse(
                saved.id().value(),
                saved.contactId().value(),
                saved.patientId().value(),
                saved.primaryContact(),
                saved.emergencyContact(),
                saved.relationshipTypeId().value());
    }
}
