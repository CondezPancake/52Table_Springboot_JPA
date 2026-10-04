package springboot.domain.common.exception;

import springboot.domain.phonecontact.model.valueobject.PhoneContactId;

public class PhoneContactNotFoundException extends RuntimeException {
    public PhoneContactNotFoundException(PhoneContactId id) {
        super("PhoneContact not found with id: " + id.value());
    }
}
