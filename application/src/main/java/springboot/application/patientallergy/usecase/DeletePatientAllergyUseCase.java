package springboot.application.patientallergy.usecase;

import java.time.LocalDateTime;

import springboot.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import springboot.domain.patientallergy.event.PatientAllergyDeletedEvent;
import springboot.domain.patientallergy.model.valueobject.PatientAllergyId;
import springboot.domain.patientallergy.port.repository.PatientAllergyRepository;

public class DeletePatientAllergyUseCase {
    private final PatientAllergyRepository repository;
    public DeletePatientAllergyUseCase(PatientAllergyRepository repository) { this.repository = repository; }

    public PatientAllergyDeletedEvent execute(PatientAllergyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new PatientAllergyNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new PatientAllergyDeletedEvent(id, LocalDateTime.now());
    }
}
