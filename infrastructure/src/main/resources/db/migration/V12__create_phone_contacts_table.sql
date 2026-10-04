CREATE TABLE phone_contacts (
    id CHAR(36) NOT NULL,
    contact_id CHAR(36) NOT NULL,
    phone VARCHAR(30),
    notes TEXT NOT NULL,
    CONSTRAINT pk_phone_contacts PRIMARY KEY (id),
    CONSTRAINT fk_phone_contacts_contact
        FOREIGN KEY (contact_id) REFERENCES contacts (id)
) ENGINE = InnoDB;
--