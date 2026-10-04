CREATE TABLE professionals (
    id CHAR(36) NOT NULL,
    document_type_id CHAR(36) NOT NULL,
    document_number VARCHAR(30) NOT NULL,
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    professional_type CHAR(36) NOT NULL,
    license_number VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL,
    city_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_professionals PRIMARY KEY (id),
    CONSTRAINT uk_professionals_document_number UNIQUE (document_number),
    CONSTRAINT uk_professionals_first_name UNIQUE (first_name),
    CONSTRAINT uk_professionals_last_name UNIQUE (last_name),
    CONSTRAINT uk_professionals_license_number UNIQUE (license_number),
    CONSTRAINT fk_professionals_document_type
        FOREIGN KEY (document_type_id) REFERENCES document_types (id),
    CONSTRAINT fk_professionals_professional_type
        FOREIGN KEY (professional_type) REFERENCES professional_types (id),
    CONSTRAINT fk_professionals_city
        FOREIGN KEY (city_id) REFERENCES city_municipalities (id)
) ENGINE = InnoDB;
