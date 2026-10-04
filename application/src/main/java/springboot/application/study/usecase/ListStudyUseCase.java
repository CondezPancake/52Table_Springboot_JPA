package springboot.application.study.usecase;

import java.util.List;

import springboot.application.study.dto.StudyResponse;
import springboot.domain.study.port.repository.StudyRepository;

public class ListStudyUseCase {
    private final StudyRepository repository;
    public ListStudyUseCase(StudyRepository repository) { this.repository = repository; }

    public List<StudyResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new StudyResponse(
                                aggregate.id().value(),
                                aggregate.name(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
