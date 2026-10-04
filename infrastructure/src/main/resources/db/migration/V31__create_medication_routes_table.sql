CREATE TABLE medication_routes (
    id CHAR(36) NOT NULL,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_medication_routes PRIMARY KEY (id),
    CONSTRAINT uk_medication_routes_code UNIQUE (code)
) ENGINE = InnoDB;
