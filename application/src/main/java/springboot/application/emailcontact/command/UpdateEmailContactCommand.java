package springboot.application.emailcontact.command;

import java.util.Objects;


import springboot.domain.emailcontact.model.valueobject.EmailContactId;
import springboot.domain.contact.model.valueobject.ContactId;

public record UpdateEmailContactCommand(
        EmailContactId id,
        ContactId contactId,
        String email,
        String notes
) {
    public UpdateEmailContactCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(contactId, "contactId must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(notes, "notes must not be null");
    }
}
