CREATE TABLE contacts (
    id CHAR(36) NOT NULL,
    full_name VARCHAR(200) NOT NULL,
    email VARCHAR(150) NOT NULL,
    notes TEXT NOT NULL,
    city_id CHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    created_by CHAR(36) NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    updated_by CHAR(36),
    CONSTRAINT pk_contacts PRIMARY KEY (id),
    CONSTRAINT uk_contacts_email UNIQUE (email),
    CONSTRAINT fk_contacts_city
        FOREIGN KEY (city_id) REFERENCES city_municipalities (id),
    CONSTRAINT fk_contacts_created_by
        FOREIGN KEY (created_by) REFERENCES professionals (id),
    CONSTRAINT fk_contacts_updated_by
        FOREIGN KEY (updated_by) REFERENCES professionals (id)
) ENGINE = InnoDB;
--