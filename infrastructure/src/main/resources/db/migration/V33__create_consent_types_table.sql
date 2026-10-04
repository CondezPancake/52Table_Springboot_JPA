CREATE TABLE consent_types (
    id CHAR(36) NOT NULL,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_consent_types PRIMARY KEY (id),
    CONSTRAINT uk_consent_types_code UNIQUE (code)
) ENGINE = InnoDB;
