package springboot.application.clinicalnote.usecase;

import springboot.application.clinicalnote.command.RegisterClinicalNoteCommand;
import springboot.application.clinicalnote.dto.ClinicalNoteResponse;
import springboot.domain.clinicalnote.model.aggregate.ClinicalNote;
import springboot.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class RegisterClinicalNoteUseCase {
    private final ClinicalNoteRepository repository;
    public RegisterClinicalNoteUseCase(ClinicalNoteRepository repository) { this.repository = repository; }

    public ClinicalNoteResponse execute(RegisterClinicalNoteCommand command) {
        ClinicalNote aggregate = ClinicalNote.register(
                command.encounterId(),
                command.professionalId(),
                command.subjective(),
                command.objective(),
                command.assessment(),
                command.plan(),
                command.additionalNotes(),
                command.signedAt());
        ClinicalNote saved = repository.save(aggregate);
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
