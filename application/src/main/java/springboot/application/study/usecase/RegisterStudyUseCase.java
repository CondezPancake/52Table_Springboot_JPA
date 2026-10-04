package springboot.application.study.usecase;

import springboot.application.study.command.RegisterStudyCommand;
import springboot.application.study.dto.StudyResponse;
import springboot.domain.study.model.aggregate.Study;
import springboot.domain.study.port.repository.StudyRepository;

public class RegisterStudyUseCase {
    private final StudyRepository repository;
    public RegisterStudyUseCase(StudyRepository repository) { this.repository = repository; }

    public StudyResponse execute(RegisterStudyCommand command) {
        Study aggregate = Study.register(
                command.name());
        Study saved = repository.save(aggregate);
        return new StudyResponse(
                saved.id().value(),
                saved.name(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
