package springboot.application.patient.usecase;

import java.time.LocalDateTime;

import springboot.application.patient.exception.PatientNotFoundApplicationException;
import springboot.domain.patient.event.PatientDeletedEvent;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.patient.port.repository.PatientRepository;

public class DeletePatientUseCase {
    private final PatientRepository repository;
    public DeletePatientUseCase(PatientRepository repository) { this.repository = repository; }

    public PatientDeletedEvent execute(PatientId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PatientNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new PatientDeletedEvent(id, LocalDateTime.now());
    }
}
