package springboot.application.contact.usecase;

import java.util.List;

import springboot.application.contact.dto.ContactResponse;
import springboot.domain.contact.port.repository.ContactRepository;

public class ListContactUseCase {
    private final ContactRepository repository;
    public ListContactUseCase(ContactRepository repository) { this.repository = repository; }

    public List<ContactResponse> execute() {
        return repository.findAll().stream()
                .map(aggregate -> new ContactResponse(
                                aggregate.id().value(),
                                aggregate.fullName(),
                                aggregate.email(),
                                aggregate.notes(),
                                aggregate.cityId().value(),
                                aggregate.createdBy().value(),
                                aggregate.updatedBy() == null ? null : aggregate.updatedBy().value(),
                                aggregate.createdAt(),
                                aggregate.updatedAt()))
                .toList();
    }
}
