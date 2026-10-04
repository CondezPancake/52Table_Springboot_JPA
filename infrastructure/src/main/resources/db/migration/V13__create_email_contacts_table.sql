CREATE TABLE email_contacts (
    id CHAR(36) NOT NULL,
    contact_id CHAR(36) NOT NULL,
    email VARCHAR(150) NOT NULL,
    notes TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT pk_email_contacts PRIMARY KEY (id),
    CONSTRAINT uk_email_contacts_email UNIQUE (email),
    CONSTRAINT fk_email_contacts_contact
        FOREIGN KEY (contact_id) REFERENCES contacts (id)
) ENGINE = InnoDB;
--