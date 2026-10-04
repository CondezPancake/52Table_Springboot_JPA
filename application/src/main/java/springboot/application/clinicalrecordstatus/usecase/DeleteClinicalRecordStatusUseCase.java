package springboot.application.clinicalrecordstatus.usecase;

import java.time.LocalDateTime;

import springboot.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import springboot.domain.clinicalrecordstatus.event.ClinicalRecordStatusDeletedEvent;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import springboot.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class DeleteClinicalRecordStatusUseCase {
    private final ClinicalRecordStatusRepository repository;
    public DeleteClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) { this.repository = repository; }

    public ClinicalRecordStatusDeletedEvent execute(ClinicalRecordStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClinicalRecordStatusNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ClinicalRecordStatusDeletedEvent(id, LocalDateTime.now());
    }
}
