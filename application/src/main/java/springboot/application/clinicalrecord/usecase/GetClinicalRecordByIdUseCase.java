package springboot.application.clinicalrecord.usecase;

import springboot.application.clinicalrecord.dto.ClinicalRecordResponse;
import springboot.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import springboot.domain.clinicalrecord.model.valueobject.ClinicalRecordId;
import springboot.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class GetClinicalRecordByIdUseCase {
    private final ClinicalRecordRepository repository;
    public GetClinicalRecordByIdUseCase(ClinicalRecordRepository repository) { this.repository = repository; }

    public ClinicalRecordResponse execute(ClinicalRecordId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClinicalRecordNotFoundApplicationException(id.value().toString()));
        return new ClinicalRecordResponse(
                aggregate.id().value(),
                aggregate.patientId().value(),
                aggregate.creationDate(),
                aggregate.recordNumber(),
                aggregate.openedAt(),
                aggregate.closedAt(),
                aggregate.statusId().value(),
                aggregate.createdBy().value(),
                aggregate.createdAt());
    }
}
