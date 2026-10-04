CREATE TABLE countries (
    id CHAR(36) NOT NULL,
    name_country VARCHAR(50) NOT NULL,
    code_country VARCHAR(10) NOT NULL,
    description VARCHAR(100) NOT NULL,
    is_active BOOLEAN NOT NULL,
    telephone_prefix VARCHAR(5) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_countries PRIMARY KEY (id)
) ENGINE = InnoDB;
