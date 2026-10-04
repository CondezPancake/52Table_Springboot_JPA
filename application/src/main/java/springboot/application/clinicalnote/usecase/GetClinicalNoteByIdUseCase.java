package springboot.application.clinicalnote.usecase;

import springboot.application.clinicalnote.dto.ClinicalNoteResponse;
import springboot.application.clinicalnote.exception.ClinicalNoteNotFoundApplicationException;
import springboot.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import springboot.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class GetClinicalNoteByIdUseCase {
    private final ClinicalNoteRepository repository;
    public GetClinicalNoteByIdUseCase(ClinicalNoteRepository repository) { this.repository = repository; }

    public ClinicalNoteResponse execute(ClinicalNoteId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ClinicalNoteNotFoundApplicationException(id.value().toString()));
        return new ClinicalNoteResponse(
                aggregate.id().value(),
                aggregate.encounterId().value(),
                aggregate.professionalId().value(),
                aggregate.subjective(),
                aggregate.objective(),
                aggregate.assessment(),
                aggregate.plan(),
                aggregate.additionalNotes(),
                aggregate.signedAt(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
