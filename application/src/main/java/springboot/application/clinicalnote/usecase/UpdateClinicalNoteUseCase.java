package springboot.application.clinicalnote.usecase;

import springboot.application.clinicalnote.command.UpdateClinicalNoteCommand;
import springboot.application.clinicalnote.dto.ClinicalNoteResponse;
import springboot.application.clinicalnote.exception.ClinicalNoteNotFoundApplicationException;
import springboot.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class UpdateClinicalNoteUseCase {
    private final ClinicalNoteRepository repository;
    public UpdateClinicalNoteUseCase(ClinicalNoteRepository repository) { this.repository = repository; }

    public ClinicalNoteResponse execute(UpdateClinicalNoteCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ClinicalNoteNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.encounterId(),
                command.professionalId(),
                command.subjective(),
                command.objective(),
                command.assessment(),
                command.plan(),
                command.additionalNotes(),
                command.signedAt());
        var saved = repository.save(aggregate);
        return new ClinicalNoteResponse(
                saved.id().value(),
                saved.encounterId().value(),
                saved.professionalId().value(),
                saved.subjective(),
                saved.objective(),
                saved.assessment(),
                saved.plan(),
                saved.additionalNotes(),
                saved.signedAt(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
