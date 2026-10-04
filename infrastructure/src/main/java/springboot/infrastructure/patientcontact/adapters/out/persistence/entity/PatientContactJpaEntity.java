package springboot.infrastructure.patientcontact.adapters.out.persistence.entity;



import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "patient_contacts")
public class PatientContactJpaEntity {
    @Id
    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID id;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "contact_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID contactId;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "patient_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID patientId;

    @Column(name = "is_primary_contact", nullable = false)
    private boolean primaryContact;

    @Column(name = "is_emergency_contact", nullable = false)
    private boolean emergencyContact;

    @JdbcTypeCode(SqlTypes.CHAR)
    @Column(name = "relationship_type_id", nullable = false, length = 36, columnDefinition = "char(36)")
    private UUID relationshipTypeId;



    public PatientContactJpaEntity() { }
    public PatientContactJpaEntity(
            UUID id,
            UUID contactId,
            UUID patientId,
            boolean primaryContact,
            boolean emergencyContact,
            UUID relationshipTypeId) {
        this.id = id;
        this.contactId = contactId;
        this.patientId = patientId;
        this.primaryContact = primaryContact;
        this.emergencyContact = emergencyContact;
        this.relationshipTypeId = relationshipTypeId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getContactId() {
        return contactId;
    }

    public void setContactId(UUID contactId) {
        this.contactId = contactId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public boolean isPrimaryContact() {
        return primaryContact;
    }

    public void setPrimaryContact(boolean primaryContact) {
        this.primaryContact = primaryContact;
    }

    public boolean isEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(boolean emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public UUID getRelationshipTypeId() {
        return relationshipTypeId;
    }

    public void setRelationshipTypeId(UUID relationshipTypeId) {
        this.relationshipTypeId = relationshipTypeId;
    }


}
