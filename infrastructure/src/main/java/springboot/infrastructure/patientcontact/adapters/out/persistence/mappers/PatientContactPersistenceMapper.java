package springboot.infrastructure.patientcontact.adapters.out.persistence.mappers;

import springboot.domain.patientcontact.model.aggregate.PatientContact;
import springboot.domain.patientcontact.model.valueobject.PatientContactId;
import springboot.domain.contact.model.valueobject.ContactId;
import springboot.domain.patient.model.valueobject.PatientId;
import springboot.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import springboot.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;

public class PatientContactPersistenceMapper {
    public PatientContactJpaEntity toJpa(PatientContact domain) {
        if (domain == null) { return null; }
        PatientContactJpaEntity jpa = new PatientContactJpaEntity();
        jpa.setId(domain.id().value());
        jpa.setContactId(domain.contactId().value());
        jpa.setPatientId(domain.patientId().value());
        jpa.setPrimaryContact(domain.primaryContact());
        jpa.setEmergencyContact(domain.emergencyContact());
        jpa.setRelationshipTypeId(domain.relationshipTypeId().value());

        return jpa;
    }

    public PatientContact toDomain(PatientContactJpaEntity jpa) {
        if (jpa == null) { return null; }
        return PatientContact.restore(
                new PatientContactId(jpa.getId()),
                new ContactId(jpa.getContactId()),
                new PatientId(jpa.getPatientId()),
                jpa.isPrimaryContact(),
                jpa.isEmergencyContact(),
                new RelationshipTypeId(jpa.getRelationshipTypeId()));
    }
}
