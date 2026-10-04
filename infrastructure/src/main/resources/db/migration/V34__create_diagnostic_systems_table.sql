CREATE TABLE diagnostic_systems (
    id CHAR(36) NOT NULL,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    version VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_diagnostic_systems PRIMARY KEY (id),
    CONSTRAINT uk_diagnostic_systems_code UNIQUE (code)
) ENGINE = InnoDB;
