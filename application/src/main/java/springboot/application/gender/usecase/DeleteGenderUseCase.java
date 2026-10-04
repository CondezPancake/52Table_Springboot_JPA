package springboot.application.gender.usecase;

import java.time.LocalDateTime;

import springboot.application.gender.exception.GenderNotFoundApplicationException;
import springboot.domain.gender.event.GenderDeletedEvent;
import springboot.domain.gender.model.valueobject.GenderId;
import springboot.domain.gender.port.repository.GenderRepository;

public class DeleteGenderUseCase {
    private final GenderRepository repository;
    public DeleteGenderUseCase(GenderRepository repository) { this.repository = repository; }

    public GenderDeletedEvent execute(GenderId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new GenderNotFoundApplicationException(id.value().toString()));
        repository.delete(aggregate);
        return new GenderDeletedEvent(id, LocalDateTime.now());
    }
}
