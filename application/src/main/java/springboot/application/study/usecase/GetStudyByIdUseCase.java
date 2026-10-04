package springboot.application.study.usecase;

import springboot.application.study.dto.StudyResponse;
import springboot.application.study.exception.StudyNotFoundApplicationException;
import springboot.domain.study.model.valueobject.StudyId;
import springboot.domain.study.port.repository.StudyRepository;

public class GetStudyByIdUseCase {
    private final StudyRepository repository;
    public GetStudyByIdUseCase(StudyRepository repository) { this.repository = repository; }

    public StudyResponse execute(StudyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new StudyNotFoundApplicationException(id.value().toString()));
        return new StudyResponse(
                aggregate.id().value(),
                aggregate.name(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
