package springboot.application.professionalstudy.usecase;

import springboot.application.professionalstudy.command.RegisterProfessionalStudyCommand;
import springboot.application.professionalstudy.dto.ProfessionalStudyResponse;
import springboot.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import springboot.domain.professionalstudy.port.repository.ProfessionalStudyRepository;

public class RegisterProfessionalStudyUseCase {
    private final ProfessionalStudyRepository repository;
    public RegisterProfessionalStudyUseCase(ProfessionalStudyRepository repository) { this.repository = repository; }

    public ProfessionalStudyResponse execute(RegisterProfessionalStudyCommand command) {
        ProfessionalStudy aggregate = ProfessionalStudy.register(
                command.studyId(),
                command.professionalId(),
                command.title(),
                command.university(),
                command.valid(),
                command.resolutionNumber(),
                command.countryId());
        ProfessionalStudy saved = repository.save(aggregate);
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
