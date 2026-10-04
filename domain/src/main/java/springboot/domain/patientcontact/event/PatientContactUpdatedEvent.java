package springboot.domain.patientcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;


import springboot.domain.common.event.DomainEvent;
import springboot.domain.patientcontact.model.valueobject.PatientContactId;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public record PatientContactUpdatedEvent(
        PatientContactId id,
        ContactId contactId,
        PatientId patientId,
        boolean primaryContact,
        boolean emergencyContact,
        RelationshipTypeId relationshipTypeId,
        LocalDateTime occurredOn
) implements DomainEvent {
    public PatientContactUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(contactId, "contactId must not be null");
        Objects.requireNonNull(patientId, "patientId must not be null");
        Objects.requireNonNull(relationshipTypeId, "relationshipTypeId must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
