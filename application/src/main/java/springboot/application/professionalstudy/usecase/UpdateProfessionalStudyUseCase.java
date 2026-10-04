package springboot.application.professionalstudy.usecase;

import springboot.application.professionalstudy.command.UpdateProfessionalStudyCommand;
import springboot.application.professionalstudy.dto.ProfessionalStudyResponse;
import springboot.application.professionalstudy.exception.ProfessionalStudyNotFoundApplicationException;
import springboot.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class UpdateProfessionalStudyUseCase {
    private final ProfessionalStudyRepository repository;
    public UpdateProfessionalStudyUseCase(ProfessionalStudyRepository repository) { this.repository = repository; }

    public ProfessionalStudyResponse execute(UpdateProfessionalStudyCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ProfessionalStudyNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.studyId(),
                command.professionalId(),
                command.title(),
                command.university(),
                command.valid(),
                command.resolutionNumber(),
                command.countryId());
        var saved = repository.save(aggregate);
        return new ProfessionalStudyResponse(
                saved.id().value(),
                saved.studyId().value(),
                saved.professionalId().value(),
                saved.title(),
                saved.university(),
                saved.valid(),
                saved.resolutionNumber(),
                saved.countryId().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
