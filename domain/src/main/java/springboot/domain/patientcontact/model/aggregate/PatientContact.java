package springboot.domain.patientcontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.model.AggregateRoot;
import springboot.domain.patientcontact.event.PatientContactRegisteredEvent;
import springboot.domain.patientcontact.event.PatientContactUpdatedEvent;
import springboot.domain.patientcontact.model.valueobject.PatientContactId;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public class PatientContact extends AggregateRoot {
    private final PatientContactId id;
    private ContactId contactId;
    private PatientId patientId;
    private boolean primaryContact;
    private boolean emergencyContact;
    private RelationshipTypeId relationshipTypeId;


    private PatientContact(
            PatientContactId id,
            ContactId contactId,
            PatientId patientId,
            boolean primaryContact,
            boolean emergencyContact,
            RelationshipTypeId relationshipTypeId) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.contactId = Objects.requireNonNull(contactId, "contactId must not be null");
        this.patientId = Objects.requireNonNull(patientId, "patientId must not be null");
        this.primaryContact = primaryContact;
        this.emergencyContact = emergencyContact;
        this.relationshipTypeId = Objects.requireNonNull(relationshipTypeId, "relationshipTypeId must not be null");

    }

    public static PatientContact register(
            ContactId contactId,
            PatientId patientId,
            boolean primaryContact,
            boolean emergencyContact,
            RelationshipTypeId relationshipTypeId) {
        PatientContactId id = PatientContactId.generate();
        LocalDateTime occurredOn = LocalDateTime.now();
        PatientContact aggregate = new PatientContact(
                id,
                contactId,
                patientId,
                primaryContact,
                emergencyContact,
                relationshipTypeId);
        aggregate.recordEvent(new PatientContactRegisteredEvent(id, occurredOn));
        return aggregate;
    }

    public static PatientContact restore(
            PatientContactId id,
            ContactId contactId,
            PatientId patientId,
            boolean primaryContact,
            boolean emergencyContact,
            RelationshipTypeId relationshipTypeId) {
        return new PatientContact(
                id,
                contactId,
                patientId,
                primaryContact,
                emergencyContact,
                relationshipTypeId);
    }

    public void update(
            ContactId contactId,
            PatientId patientId,
            boolean primaryContact,
            boolean emergencyContact,
            RelationshipTypeId relationshipTypeId) {
        this.contactId = Objects.requireNonNull(contactId, "contactId must not be null");
        this.patientId = Objects.requireNonNull(patientId, "patientId must not be null");
        this.primaryContact = primaryContact;
        this.emergencyContact = emergencyContact;
        this.relationshipTypeId = Objects.requireNonNull(relationshipTypeId, "relationshipTypeId must not be null");
        LocalDateTime occurredOn = LocalDateTime.now();
        recordEvent(new PatientContactUpdatedEvent(
                        this.id,
                        this.contactId,
                        this.patientId,
                        this.primaryContact,
                        this.emergencyContact,
                        this.relationshipTypeId,
                        occurredOn));
    }

    public PatientContactId id() {
        return id;
    }

    public ContactId contactId() {
        return contactId;
    }

    public PatientId patientId() {
        return patientId;
    }

    public boolean primaryContact() {
        return primaryContact;
    }

    public boolean emergencyContact() {
        return emergencyContact;
    }

    public RelationshipTypeId relationshipTypeId() {
        return relationshipTypeId;
    }


}
