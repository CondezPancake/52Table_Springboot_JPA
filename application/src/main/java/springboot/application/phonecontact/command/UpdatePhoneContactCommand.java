package springboot.application.phonecontact.command;

import java.util.Objects;


import springboot.domain.phonecontact.model.valueobject.PhoneContactId;
import springboot.domain.contact.model.valueobject.ContactId;

public record UpdatePhoneContactCommand(
        PhoneContactId id,
        ContactId contactId,
        String phone,
        String notes
) {
    public UpdatePhoneContactCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(contactId, "contactId must not be null");
        Objects.requireNonNull(notes, "notes must not be null");
    }
}
