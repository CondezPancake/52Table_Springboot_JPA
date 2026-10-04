package springboot.application.gender.usecase;

import springboot.application.gender.dto.GenderResponse;
import springboot.application.gender.exception.GenderNotFoundApplicationException;
import springboot.domain.gender.model.valueobject.GenderId;
import springboot.domain.gender.port.repository.GenderRepository;

public class GetGenderByIdUseCase {
    private final GenderRepository repository;
    public GetGenderByIdUseCase(GenderRepository repository) { this.repository = repository; }

    public GenderResponse execute(GenderId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new GenderNotFoundApplicationException(id.value().toString()));
        return new GenderResponse(
                aggregate.id().value(),
                aggregate.description(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
