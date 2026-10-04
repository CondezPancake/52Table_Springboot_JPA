package springboot.application.contact.usecase;

import springboot.application.contact.command.RegisterContactCommand;
import springboot.application.contact.dto.ContactResponse;
import springboot.domain.contact.model.aggregate.Contact;
import springboot.domain.contact.port.repository.ContactRepository;

public class RegisterContactUseCase {
    private final ContactRepository repository;
    public RegisterContactUseCase(ContactRepository repository) { this.repository = repository; }

    public ContactResponse execute(RegisterContactCommand command) {
        Contact aggregate = Contact.register(
                command.fullName(),
                command.email(),
                command.notes(),
                command.cityId(),
                command.createdBy(),
                command.updatedBy());
        Contact saved = repository.save(aggregate);
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
