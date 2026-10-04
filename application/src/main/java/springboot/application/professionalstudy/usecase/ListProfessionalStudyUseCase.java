package springboot.application.professionalstudy.usecase;

import java.util.List;

import springboot.application.professionalstudy.dto.ProfessionalStudyResponse;
import springboot.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class ListProfessionalStudyUseCase {
    private final ProfessionalStudyRepository repository;
    public ListProfessionalStudyUseCase(ProfessionalStudyRepository repository) { this.repository = repository; }

    public List<ProfessionalStudyResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ProfessionalStudyResponse(
                                aggregate.id().value(),
                                aggregate.studyId().value(),
                                aggregate.professionalId().value(),
                                aggregate.title(),
                                aggregate.university(),
                                aggregate.valid(),
                                aggregate.resolutionNumber(),
                                aggregate.countryId().value(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
