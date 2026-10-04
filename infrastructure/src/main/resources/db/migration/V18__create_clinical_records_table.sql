CREATE TABLE clinical_records (
    id CHAR(36) NOT NULL,
    patient_id CHAR(36) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    record_number VARCHAR(50) NOT NULL,
    opened_at TIMESTAMP NOT NULL,
    closed_at TIMESTAMP NOT NULL,
    status_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by CHAR(36) NOT NULL,
    CONSTRAINT pk_clinical_records PRIMARY KEY (id),
    CONSTRAINT fk_clinical_records_patient
        FOREIGN KEY (patient_id) REFERENCES patients (id),
    CONSTRAINT fk_clinical_records_status
        FOREIGN KEY (status_id) REFERENCES clinical_record_statuses (id),
    CONSTRAINT fk_clinical_records_created_by
        FOREIGN KEY (created_by) REFERENCES professionals (id)
) ENGINE = InnoDB;
--