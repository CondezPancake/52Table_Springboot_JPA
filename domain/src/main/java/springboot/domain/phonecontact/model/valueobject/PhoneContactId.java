package springboot.domain.phonecontact.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record PhoneContactId(UUID value) {
    public PhoneContactId {
        Objects.requireNonNull(value, "PhoneContactId value must not be null");
    }

    public static PhoneContactId generate() {
        return new PhoneContactId(UUID.randomUUID());
    }
}
