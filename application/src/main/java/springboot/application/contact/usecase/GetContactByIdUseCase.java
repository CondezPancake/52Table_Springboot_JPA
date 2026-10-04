package springboot.application.contact.usecase;

import springboot.application.contact.dto.ContactResponse;
import springboot.application.contact.exception.ContactNotFoundApplicationException;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.contact.port.repository.ContactRepository;

public class GetContactByIdUseCase {
    private final ContactRepository repository;
    public GetContactByIdUseCase(ContactRepository repository) { this.repository = repository; }

    public ContactResponse execute(ContactId id) {
        var aggregate = repository.findById(id)
                .orElseThrow(() -> new ContactNotFoundApplicationException(id.value().toString()));
        return new ContactResponse(
                aggregate.id().value(),
                aggregate.fullName(),
                aggregate.email(),
                aggregate.notes(),
                aggregate.cityId().value(),
                aggregate.createdBy().value(),
                aggregate.updatedBy() == null ? null : aggregate.updatedBy().value(),
                aggregate.createdAt(),
                aggregate.updatedAt());
    }
}
