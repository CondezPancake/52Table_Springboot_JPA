package springboot.domain.emailcontact.model.valueobject;

import java.util.Objects;
import java.util.UUID;

public record EmailContactId(UUID value) {
    public EmailContactId {
        Objects.requireNonNull(value, "EmailContactId value must not be null");
    }

    public static EmailContactId generate() {
        return new EmailContactId(UUID.randomUUID());
    }
}
