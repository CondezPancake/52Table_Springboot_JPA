package springboot.application.clinicalnote.usecase;

import java.util.List;

import springboot.application.clinicalnote.dto.ClinicalNoteResponse;
import springboot.domain.clinicalnote.port.repository.ClinicalNoteRepository;

public class ListClinicalNoteUseCase {
    private final ClinicalNoteRepository repository;
    public ListClinicalNoteUseCase(ClinicalNoteRepository repository) { this.repository = repository; }

    public List<ClinicalNoteResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ClinicalNoteResponse(
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
                                aggregate.updatedAt()))
                .toList();
    }
}
