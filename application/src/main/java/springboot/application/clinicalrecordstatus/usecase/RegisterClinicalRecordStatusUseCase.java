package springboot.application.clinicalrecordstatus.usecase;

import springboot.application.clinicalrecordstatus.command.RegisterClinicalRecordStatusCommand;
import springboot.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import springboot.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import springboot.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class RegisterClinicalRecordStatusUseCase {
    private final ClinicalRecordStatusRepository repository;
    public RegisterClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) { this.repository = repository; }

    public ClinicalRecordStatusResponse execute(RegisterClinicalRecordStatusCommand command) {
        ClinicalRecordStatus aggregate = ClinicalRecordStatus.register(
                command.code(),
                command.name());
        ClinicalRecordStatus saved = repository.save(aggregate);
        return new ClinicalRecordStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
