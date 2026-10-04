package springboot.application.clinicalrecord.usecase;

import java.util.List;

import springboot.application.clinicalrecord.dto.ClinicalRecordResponse;
import springboot.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class ListClinicalRecordUseCase {
    private final ClinicalRecordRepository repository;
    public ListClinicalRecordUseCase(ClinicalRecordRepository repository) { this.repository = repository; }

    public List<ClinicalRecordResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ClinicalRecordResponse(
                                aggregate.id().value(),
                                aggregate.patientId().value(),
                                aggregate.creationDate(),
                                aggregate.recordNumber(),
                                aggregate.openedAt(),
                                aggregate.closedAt(),
                                aggregate.statusId().value(),
                                aggregate.createdBy().value(),
                                aggregate.createdAt()))
                .toList();
    }
}
