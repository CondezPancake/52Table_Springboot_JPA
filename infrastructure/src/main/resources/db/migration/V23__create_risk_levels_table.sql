CREATE TABLE risk_levels (
    id CHAR(36) NOT NULL,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    severity INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_risk_levels PRIMARY KEY (id),
    CONSTRAINT uk_risk_levels_code UNIQUE (code),
    CONSTRAINT uk_risk_levels_name UNIQUE (name)
) ENGINE = InnoDB;
