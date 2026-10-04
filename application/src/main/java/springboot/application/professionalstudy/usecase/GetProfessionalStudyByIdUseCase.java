package springboot.application.professionalstudy.usecase;

import springboot.application.professionalstudy.dto.ProfessionalStudyResponse;
import springboot.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import springboot.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import springboot.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class GetProfessionalStudyByIdUseCase {
    private final ProfessionalStudyRepository repository;
    public GetProfessionalStudyByIdUseCase(ProfessionalStudyRepository repository) { this.repository = repository; }

    public ProfessionalStudyResponse execute(ProfessionalStudyId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ProfessionalStudyNotFoundApplicationException(id.value().toString()));
        return new ProfessionalStudyResponse(
                aggregate.id().value(),
                aggregate.studyId().value(),
                aggregate.professionalId().value(),
                aggregate.title(),
                aggregate.university(),
                aggregate.valid(),
                aggregate.resolutionNumber(),
                aggregate.countryId().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
