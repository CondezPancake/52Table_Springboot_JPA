package springboot.application.patientcontact.usecase;

import java.time.LocalDateTime;

import springboot.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import springboot.domain.patientcontact.event.PatientContactDeletedEvent;
import springboot.domain.patientcontact.model.valueobject.PatientContactId;
import springboot.domain.patientcontact.port.repository.PatientContactRepository;

public class DeletePatientContactUseCase {
    private final PatientContactRepository repository;
    public DeletePatientContactUseCase(PatientContactRepository repository) { this.repository = repository; }

    public PatientContactDeletedEvent execute(PatientContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PatientContactNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new PatientContactDeletedEvent(id, LocalDateTime.now());
    }
}
