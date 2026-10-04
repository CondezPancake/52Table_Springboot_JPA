CREATE TABLE encounter_types (
    id CHAR(36) NOT NULL,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_encounter_types PRIMARY KEY (id),
    CONSTRAINT uk_encounter_types_code UNIQUE (code),
    CONSTRAINT uk_encounter_types_name UNIQUE (name)
) ENGINE = InnoDB;
--