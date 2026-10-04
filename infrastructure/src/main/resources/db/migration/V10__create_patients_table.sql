CREATE TABLE patients (
    id CHAR(36) NOT NULL,
    document_type_id CHAR(36) NOT NULL,
    document_number VARCHAR(30) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    middle_name VARCHAR(50),
    last_name VARCHAR(50) NOT NULL,
    second_last_name VARCHAR(50),
    birth_date DATE NOT NULL,
    biological_sex_id CHAR(36) NOT NULL,
    gender_identity CHAR(36) NOT NULL,
    email VARCHAR(150) NOT NULL,
    phone VARCHAR(30) NOT NULL,
    address VARCHAR(250) NOT NULL,
    active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by CHAR(36),
    updated_at TIMESTAMP NOT NULL,
    updated_by CHAR(36),
    city_id CHAR(36) NOT NULL,
    CONSTRAINT pk_patients PRIMARY KEY (id),
    CONSTRAINT uk_patients_email UNIQUE (email),
    CONSTRAINT fk_patients_document_type
        FOREIGN KEY (document_type_id) REFERENCES document_types (id),
    CONSTRAINT fk_patients_biological_sex
        FOREIGN KEY (biological_sex_id) REFERENCES genders (id),
    CONSTRAINT fk_patients_gender_identity
        FOREIGN KEY (gender_identity) REFERENCES genders (id),
    CONSTRAINT fk_patients_created_by
        FOREIGN KEY (created_by) REFERENCES professionals (id),
    CONSTRAINT fk_patients_updated_by
        FOREIGN KEY (updated_by) REFERENCES professionals (id),
    CONSTRAINT fk_patients_city
        FOREIGN KEY (city_id) REFERENCES city_municipalities (id)
) ENGINE = InnoDB;
