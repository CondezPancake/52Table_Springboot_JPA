package springboot.application.clinicalrecord.usecase;

import springboot.application.clinicalrecord.command.UpdateClinicalRecordCommand;
import springboot.application.clinicalrecord.dto.ClinicalRecordResponse;
import springboot.application.clinicalrecord.exception.ClinicalRecordNotFoundApplicationException;
import springboot.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class UpdateClinicalRecordUseCase {
    private final ClinicalRecordRepository repository;
    public UpdateClinicalRecordUseCase(ClinicalRecordRepository repository) { this.repository = repository; }

    public ClinicalRecordResponse execute(UpdateClinicalRecordCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ClinicalRecordNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.patientId(),
                command.creationDate(),
                command.recordNumber(),
                command.openedAt(),
                command.closedAt(),
                command.statusId(),
                command.createdBy());
        var saved = repository.save(aggregate);
        return new ClinicalRecordResponse(
                saved.id().value(),
                saved.patientId().value(),
                saved.creationDate(),
                saved.recordNumber(),
                saved.openedAt(),
                saved.closedAt(),
                saved.statusId().value(),
                saved.createdBy().value(),
                saved.createdAt());
    }
}
