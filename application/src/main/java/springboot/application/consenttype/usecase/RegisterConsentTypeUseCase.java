package springboot.application.consenttype.usecase;

import springboot.application.consenttype.command.RegisterConsentTypeCommand;
import springboot.application.consenttype.dto.ConsentTypeResponse;
import springboot.domain.consenttype.model.aggregate.ConsentType;
import springboot.domain.consenttype.port.repository.ConsentTypeRepository;

public class RegisterConsentTypeUseCase {
    private final ConsentTypeRepository repository;
    public RegisterConsentTypeUseCase(ConsentTypeRepository repository) { this.repository = repository; }

    public ConsentTypeResponse execute(RegisterConsentTypeCommand command) {
        ConsentType aggregate = ConsentType.register(
                command.code(),
                command.name(),
                command.active(),
                command.description());
        ConsentType saved = repository.save(aggregate);
        return new ConsentTypeResponse(
                saved.id().value(),
                saved.code(),
                saved.name(),
                saved.active(),
                saved.description(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
