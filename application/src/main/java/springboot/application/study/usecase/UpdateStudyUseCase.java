package springboot.application.study.usecase;

import springboot.application.study.command.UpdateStudyCommand;
import springboot.application.study.dto.StudyResponse;
import springboot.application.study.exception.StudyNotFoundApplicationException;
import springboot.domain.study.port.repository.StudyRepository;

public class UpdateStudyUseCase {
    private final StudyRepository repository;
    public UpdateStudyUseCase(StudyRepository repository) { this.repository = repository; }

    public StudyResponse execute(UpdateStudyCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new StudyNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.name());
        var saved = repository.save(aggregate);
        return new StudyResponse(
                saved.id().value(),
                saved.name(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
