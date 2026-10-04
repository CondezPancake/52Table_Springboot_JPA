CREATE TABLE treatment_statuses (
    id CHAR(36) NOT NULL,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_treatment_statuses PRIMARY KEY (id),
    CONSTRAINT uk_treatment_statuses_code UNIQUE (code),
    CONSTRAINT uk_treatment_statuses_name UNIQUE (name)
) ENGINE = InnoDB;
