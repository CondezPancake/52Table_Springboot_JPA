package springboot.application.clinicalrecordstatus.usecase;

import java.util.List;

import springboot.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import springboot.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class ListClinicalRecordStatusUseCase {
    private final ClinicalRecordStatusRepository repository;
    public ListClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) { this.repository = repository; }

    public List<ClinicalRecordStatusResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ClinicalRecordStatusResponse(
                                aggregate.id().value(),
                                aggregate.code(),
                                aggregate.name(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
