CREATE TABLE studies (
    id CHAR(36) NOT NULL,
    name VARCHAR(40) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_studies PRIMARY KEY (id)
) ENGINE = InnoDB;
