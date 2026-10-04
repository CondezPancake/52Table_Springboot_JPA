package springboot.application.clinicalrecord.usecase;

import springboot.application.clinicalrecord.command.RegisterClinicalRecordCommand;
import springboot.application.clinicalrecord.dto.ClinicalRecordResponse;
import springboot.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import springboot.domain.clinicalrecord.port.repository.ClinicalRecordRepository;

public class RegisterClinicalRecordUseCase {
    private final ClinicalRecordRepository repository;
    public RegisterClinicalRecordUseCase(ClinicalRecordRepository repository) { this.repository = repository; }

    public ClinicalRecordResponse execute(RegisterClinicalRecordCommand command) {
        ClinicalRecord aggregate = ClinicalRecord.register(
                command.patientId(),
                command.creationDate(),
                command.recordNumber(),
                command.openedAt(),
                command.closedAt(),
                command.statusId(),
                command.createdBy());
        ClinicalRecord saved = repository.save(aggregate);
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
