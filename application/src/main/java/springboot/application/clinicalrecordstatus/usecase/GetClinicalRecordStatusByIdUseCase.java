package springboot.application.clinicalrecordstatus.usecase;

import springboot.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import springboot.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import springboot.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import springboot.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class GetClinicalRecordStatusByIdUseCase {
    private final ClinicalRecordStatusRepository repository;
    public GetClinicalRecordStatusByIdUseCase(ClinicalRecordStatusRepository repository) { this.repository = repository; }

    public ClinicalRecordStatusResponse execute(ClinicalRecordStatusId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClinicalRecordStatusNotFoundApplicationException(id.value().toString()));
        return new ClinicalRecordStatusResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
