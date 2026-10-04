package springboot.application.clinicalrecord.usecase;

import java.time.LocalDateTime;

import springboot.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import springboot.domain.clinicalrecord.event.ClinicalRecordDeletedEvent;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class DeleteClinicalRecordUseCase {
    private final ClinicalRecordRepository repository;
    public DeleteClinicalRecordUseCase(ClinicalRecordRepository repository) { this.repository = repository; }

    public ClinicalRecordDeletedEvent execute(ClinicalRecordId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClinicalRecordNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ClinicalRecordDeletedEvent(id, LocalDateTime.now());
    }
}
