package springboot.application.assessmenttype.usecase;

import springboot.application.assessmenttype.dto.AssessmentTypeResponse;
import springboot.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import springboot.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import springboot.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class GetAssessmentTypeByIdUseCase {
    private final AssessmentTypeRepository repository;
    public GetAssessmentTypeByIdUseCase(AssessmentTypeRepository repository) { this.repository = repository; }

    public AssessmentTypeResponse execute(AssessmentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new AssessmentTypeNotFoundApplicationException(id.value().toString()));
        return new AssessmentTypeResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.description(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
