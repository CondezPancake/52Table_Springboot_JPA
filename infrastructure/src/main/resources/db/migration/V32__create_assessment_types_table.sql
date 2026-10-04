CREATE TABLE assessment_types (
    id CHAR(36) NOT NULL,
    code VARCHAR(20) NOT NULL,
    name VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_assessment_types PRIMARY KEY (id),
    CONSTRAINT uk_assessment_types_code UNIQUE (code)
) ENGINE = InnoDB;
