package springboot.application.patientcontact.command;

import java.util.Objects;


import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public record RegisterPatientContactCommand(
        ContactId contactId,
        PatientId patientId,
        boolean primaryContact,
        boolean emergencyContact,
        RelationshipTypeId relationshipTypeId
) {
    public RegisterPatientContactCommand {
        Objects.requireNonNull(contactId, "contactId must not be null");
        Objects.requireNonNull(patientId, "patientId must not be null");
        Objects.requireNonNull(relationshipTypeId, "relationshipTypeId must not be null");
    }
}
