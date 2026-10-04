package springboot.application.consenttype.usecase;

import springboot.application.consenttype.command.UpdateConsentTypeCommand;
import springboot.application.consenttype.dto.ConsentTypeResponse;
import springboot.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import springboot.domain.consenttype.port.repository.ConsentTypeRepository;

public class UpdateConsentTypeUseCase {
    private final ConsentTypeRepository repository;
    public UpdateConsentTypeUseCase(ConsentTypeRepository repository) { this.repository = repository; }

    public ConsentTypeResponse execute(UpdateConsentTypeCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ConsentTypeNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.code(),
                command.name(),
                command.active(),
                command.description());
        var saved = repository.save(aggregate);
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
