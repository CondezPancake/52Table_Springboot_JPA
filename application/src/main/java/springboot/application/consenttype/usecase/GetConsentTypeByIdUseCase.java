package springboot.application.consenttype.usecase;

import springboot.application.consenttype.dto.ConsentTypeResponse;
import springboot.application.consenttype.exception.ConsentTypeNotFoundApplicationException;
import springboot.domain.consenttype.model.valueobject.ConsentTypeId;
import springboot.domain.consenttype.port.repository.ConsentTypeRepository;

public class GetConsentTypeByIdUseCase {
    private final ConsentTypeRepository repository;
    public GetConsentTypeByIdUseCase(ConsentTypeRepository repository) { this.repository = repository; }

    public ConsentTypeResponse execute(ConsentTypeId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ConsentTypeNotFoundApplicationException(id.value().toString()));
        return new ConsentTypeResponse(
                aggregate.id().value(),
                aggregate.code(),
                aggregate.name(),
                aggregate.active(),
                aggregate.description(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
