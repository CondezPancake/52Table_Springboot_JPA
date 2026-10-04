package springboot.domain.common.exception;

import springboot.domain.contact.model.valueobject.ContactId;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(ContactId id) {
        super("Contact not found with id: " + id.value());
    }
}
