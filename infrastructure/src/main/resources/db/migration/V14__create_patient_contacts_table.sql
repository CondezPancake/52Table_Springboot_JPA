CREATE TABLE patient_contacts (
    id CHAR(36) NOT NULL,
    contact_id CHAR(36) NOT NULL,
    patient_id CHAR(36) NOT NULL,
    is_primary_contact BOOLEAN NOT NULL,
    is_emergency_contact BOOLEAN NOT NULL,
    relationship_type_id CHAR(36) NOT NULL,
    CONSTRAINT pk_patient_contacts PRIMARY KEY (id),
    CONSTRAINT fk_patient_contacts_contact
        FOREIGN KEY (contact_id) REFERENCES contacts (id),
    CONSTRAINT fk_patient_contacts_patient
        FOREIGN KEY (patient_id) REFERENCES patients (id),
    CONSTRAINT fk_patient_contacts_relationship_type
        FOREIGN KEY (relationship_type_id) REFERENCES relationship_types (id)
) ENGINE = InnoDB;
--