package springboot.application.phonecontact.command;

import java.util.Objects;


import springboot.domain.contact.model.valueobject.ContactId;

public record RegisterPhoneContactCommand(
        ContactId contactId,
        String phone,
        String notes
) {
    public RegisterPhoneContactCommand {
        Objects.requireNonNull(contactId, "contactId must not be null");
        Objects.requireNonNull(notes, "notes must not be null");
    }
}
