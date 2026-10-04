package springboot.application.professionalstudy.usecase;

import java.time.LocalDateTime;

import springboot.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import springboot.domain.professionalstudy.event.ProfessionalStudyDeletedEvent;
import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import springboot.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class DeleteProfessionalStudyUseCase {
    private final ProfessionalStudyRepository repository;
    public DeleteProfessionalStudyUseCase(ProfessionalStudyRepository repository) { this.repository = repository; }

    public ProfessionalStudyDeletedEvent execute(ProfessionalStudyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProfessionalStudyNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new ProfessionalStudyDeletedEvent(id, LocalDateTime.now());
    }
}
