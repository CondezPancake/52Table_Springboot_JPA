package springboot.application.contact.usecase;

import springboot.application.contact.command.UpdateContactCommand;
import springboot.application.contact.dto.ContactResponse;
import springboot.application.contact.exception.ContactNotFoundApplicationException;
import springboot.domain.contact.port.repository.ContactRepository;

public class UpdateContactUseCase {
    private final ContactRepository repository;
    public UpdateContactUseCase(ContactRepository repository) { this.repository = repository; }

    public ContactResponse execute(UpdateContactCommand command) {
        var aggregate = repository.findById(command.id())
                .orElseThrow(() -> new ContactNotFoundApplicationException(command.id().value().toString()));
        aggregate.update(
                command.fullName(),
                command.email(),
                command.notes(),
                command.cityId(),
                command.createdBy(),
                command.updatedBy());
        var saved = repository.save(aggregate);
        return new ContactResponse(
                saved.id().value(),
                saved.fullName(),
                saved.email(),
                saved.notes(),
                saved.cityId().value(),
                saved.createdBy().value(),
                saved.updatedBy() == null ? null : saved.updatedBy().value(),
                saved.createdAt(),
                saved.updatedAt());
    }
}
