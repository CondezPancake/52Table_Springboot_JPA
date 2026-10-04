package springboot.application.assessmenttype.usecase;

import java.time.LocalDateTime;

import springboot.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import springboot.domain.assessmenttype.event.AssessmentTypeDeletedEvent;
import springboot.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import springboot.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class DeleteAssessmentTypeUseCase {
    private final AssessmentTypeRepository repository;
    public DeleteAssessmentTypeUseCase(AssessmentTypeRepository repository) { this.repository = repository; }

    public AssessmentTypeDeletedEvent execute(AssessmentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AssessmentTypeNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new AssessmentTypeDeletedEvent(id, LocalDateTime.now());
    }
}
