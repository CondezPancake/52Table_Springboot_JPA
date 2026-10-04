package springboot.application.assessmenttype.usecase;

import springboot.application.assessmenttype.command.UpdateAssessmentTypeCommand;
import springboot.application.assessmenttype.dto.AssessmentTypeResponse;
import springboot.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import springboot.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class UpdateAssessmentTypeUseCase {
    private final AssessmentTypeRepository repository;
    public UpdateAssessmentTypeUseCase(AssessmentTypeRepository repository) { this.repository = repository; }

    public AssessmentTypeResponse execute(UpdateAssessmentTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new AssessmentTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active(),
                command.description());
        var saved = repository.save(aggregate);
        return new AssessmentTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.description(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
