CREATE TABLE clinical_record_statuses (
    id CHAR(36) NOT NULL,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_clinical_record_statuses PRIMARY KEY (id),
    CONSTRAINT uk_clinical_record_statuses_code UNIQUE (code),
    CONSTRAINT uk_clinical_record_statuses_name UNIQUE (name)
) ENGINE = InnoDB;
--