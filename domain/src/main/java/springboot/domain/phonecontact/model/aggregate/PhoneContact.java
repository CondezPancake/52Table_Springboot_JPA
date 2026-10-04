package springboot.domain.phonecontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.phonecontact.event.PhoneContactRegisteredEvent;
import springboot.domain.phonecontact.event.PhoneContactUpdatedEvent;
import springboot.domain.phonecontact.model.valueobject.PhoneContactId;
import springboot.domain.contact.model.valueobject.ContactId;

public class PhoneContact extends AggregateRoot {
    private final PhoneContactId id;
    private ContactId contactId;
    private String phone;
    private String notes;


    private PhoneContact(
            PhoneContactId id,
            ContactId contactId,
            String phone,
            String notes) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.contactId = Objects.requireNonNull(contactId, "contactId must not be null");
        this.phone = phone;
        this.notes = Objects.requireNonNull(notes, "notes must not be null");

    }

    public static PhoneContact register(
            ContactId contactId,
            String phone,
            String notes) {
        PhoneContactId id = PhoneContactId.generate();
        LocalDateTime occurredOn = LocalDateTime.now();
        PhoneContact aggregate = new PhoneContact(
                id,
                contactId,
                phone,
                notes);
        aggregate.recordEvent(new PhoneContactRegisteredEvent(id, occurredOn));
        return aggregate;
    }

    public static PhoneContact restore(
            PhoneContactId id,
            ContactId contactId,
            String phone,
            String notes) {
        return new PhoneContact(
                id,
                contactId,
                phone,
                notes);
    }

    public void update(
            ContactId contactId,
            String phone,
            String notes) {
        this.contactId = Objects.requireNonNull(contactId, "contactId must not be null");
        this.phone = phone;
        this.notes = Objects.requireNonNull(notes, "notes must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new PhoneContactUpdatedEvent(
                        this.id,
                        this.contactId,
                        this.phone,
                        this.notes,
                        occurredOn));
    }

    public PhoneContactId id() {
        return id;
    }

    public ContactId contactId() {
        return contactId;
    }

    public String phone() {
        return phone;
    }

    public String notes() {
        return notes;
    }


}
