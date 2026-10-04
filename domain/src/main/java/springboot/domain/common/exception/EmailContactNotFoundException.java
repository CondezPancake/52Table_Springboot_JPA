package springboot.domain.common.exception;

import springboot.domain.emailcontact.model.valueobject.EmailContactId;

public class EmailContactNotFoundException extends RuntimeException {
    public EmailContactNotFoundException(EmailContactId id) {
        super("EmailContact not found with id: " + id.value());
    }
}
