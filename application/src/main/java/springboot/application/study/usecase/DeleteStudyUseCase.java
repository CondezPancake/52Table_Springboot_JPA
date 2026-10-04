package springboot.application.study.usecase;

import java.time.LocalDateTime;

import springboot.application.study.exception.StudyNotFoundApplicationException;
import springboot.domain.study.event.StudyDeletedEvent;
import springboot.domain.study.model.valueobject.StudyId;
import springboot.domain.study.port.repository.StudyRepository;

public class DeleteStudyUseCase {
    private final StudyRepository repository;
    public DeleteStudyUseCase(StudyRepository repository) { this.repository = repository; }

    public StudyDeletedEvent execute(StudyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new StudyNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new StudyDeletedEvent(id, LocalDateTime.now());
    }
}
