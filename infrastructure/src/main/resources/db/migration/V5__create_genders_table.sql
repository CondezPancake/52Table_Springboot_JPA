CREATE TABLE genders (
    id CHAR(36) NOT NULL,
    description VARCHAR(50) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_genders PRIMARY KEY (id),
    CONSTRAINT uk_genders_description UNIQUE (description)
) ENGINE = InnoDB;
