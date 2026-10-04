CREATE TABLE relationship_types (
    id CHAR(36) NOT NULL,
    description VARCHAR(50) NOT NULL,
    CONSTRAINT pk_relationship_types PRIMARY KEY (id),
    CONSTRAINT uk_relationship_types_description UNIQUE (description)
) ENGINE = InnoDB;
