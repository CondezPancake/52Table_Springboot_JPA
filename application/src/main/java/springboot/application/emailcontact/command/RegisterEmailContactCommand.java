package springboot.application.emailcontact.command;

import java.util.Objects;


import springboot.domain.contact.model.valueobject.ContactId;

public record RegisterEmailContactCommand(
        ContactId contactId,
        String email,
        String notes
) {
    public RegisterEmailContactCommand {
        Objects.requireNonNull(contactId, "contactId must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(notes, "notes must not be null");
    }
}
