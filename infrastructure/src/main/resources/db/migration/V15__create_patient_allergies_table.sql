CREATE TABLE patient_allergies (
    id CHAR(36) NOT NULL,
    patient_id CHAR(36) NOT NULL,
    substance VARCHAR(200) NOT NULL,
    reaction TEXT,
    severity VARCHAR(20) NOT NULL,
    active BOOLEAN NOT NULL,
    recorded_at TIMESTAMP NOT NULL,
    recorded_by CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_patient_allergies PRIMARY KEY (id),
    CONSTRAINT fk_patient_allergies_patient
        FOREIGN KEY (patient_id) REFERENCES patients (id),
    CONSTRAINT fk_patient_allergies_recorded_by
        FOREIGN KEY (recorded_by) REFERENCES professionals (id)
) ENGINE = InnoDB;
--