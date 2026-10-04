package springboot.application.clinicalrecordstatus.usecase;

import springboot.application.clinicalrecordstatus.command.UpdateClinicalRecordStatusCommand;
import springboot.application.clinicalrecordstatus.dto.ClinicalRecordStatusResponse;
import springboot.application.clinicalrecordstatus.exception.ClinicalRecordStatusNotFoundApplicationException;
import springboot.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

public class UpdateClinicalRecordStatusUseCase {
    private final ClinicalRecordStatusRepository repository;
    public UpdateClinicalRecordStatusUseCase(ClinicalRecordStatusRepository repository) { this.repository = repository; }

    public ClinicalRecordStatusResponse execute(UpdateClinicalRecordStatusCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ClinicalRecordStatusNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name());
        var saved = repository.save(aggregate);
        return new ClinicalRecordStatusResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
