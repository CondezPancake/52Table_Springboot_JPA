CREATE TABLE professional_types (
    id CHAR(36) NOT NULL,
    name VARCHAR(40) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_professional_types PRIMARY KEY (id),
    CONSTRAINT uk_professional_types_name UNIQUE (name)
) ENGINE = InnoDB;
