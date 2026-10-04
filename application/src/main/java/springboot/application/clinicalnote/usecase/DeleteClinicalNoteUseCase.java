package springboot.application.clinicalnote.usecase;

import java.time.LocalDateTime;

import springboot.application.clinicalnote.exception.ClinicalNoteNotFoundApplicationException;
import springboot.domain.clinicalnote.event.ClinicalNoteDeletedEvent;
import springboot.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import springboot.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class DeleteClinicalNoteUseCase {
    private final ClinicalNoteRepository repository;
    public DeleteClinicalNoteUseCase(ClinicalNoteRepository repository) { this.repository = repository; }

    public ClinicalNoteDeletedEvent execute(ClinicalNoteId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClinicalNoteNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ClinicalNoteDeletedEvent(id, LocalDateTime.now());
    }
}
