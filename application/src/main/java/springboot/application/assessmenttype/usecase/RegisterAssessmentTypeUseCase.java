package springboot.application.assessmenttype.usecase;

import springboot.application.assessmenttype.command.RegisterAssessmentTypeCommand;
import springboot.application.assessmenttype.dto.AssessmentTypeResponse;
import springboot.domain.assessmenttype.model.aggregate.AssessmentType;
import springboot.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class RegisterAssessmentTypeUseCase {
    private final AssessmentTypeRepository repository;
    public RegisterAssessmentTypeUseCase(AssessmentTypeRepository repository) { this.repository = repository; }

    public AssessmentTypeResponse execute(RegisterAssessmentTypeCommand command) {
        AssessmentType aggregate = AssessmentType.register(
                command.code(),
                command.name(),
                command.active(),
                command.description());
        AssessmentType saved = repository.save(aggregate);
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
